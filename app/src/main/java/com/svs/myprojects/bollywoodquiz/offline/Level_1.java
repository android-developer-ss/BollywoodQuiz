package com.svs.myprojects.bollywoodquiz.offline;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.svs.myprojects.bollywoodquiz.R;
import com.svs.myprojects.bollywoodquiz.models.QuestionModel;
import com.svs.myprojects.bollywoodquiz.models.UserModel;
import com.svs.myprojects.bollywoodquiz.utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Level_1 extends AppCompatActivity implements View.OnClickListener {

    public static String LOG_TAG = Level_1.class.getSimpleName();
    private HashMap<Integer, QuestionModel> mQuestionModelHashMap;
    private QuestionModel mCurrentQuestionModel;
    private Button mNextButton;
    private RadioButton mRadioButtonTrue, mRadioButtonFalse;
    private RadioGroup mRadioGroup;
    private TextView mQuestionTextView, mScoreTextView, mTimerTextView;
    private ImageView mThumbImageView;
    private CountDownTimer mCountDownTimer;
    private int mRemainingSeconds;
    private int mCurrentQuestionIndex;
    private int mScore;
    private ArrayList<Integer> mShuffledQuestionNumbers;
    private AnimatorSet mFlipVerticalAnimation;
    private AnimatorSet mFlipHorizontalAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1_activity);

        setupViewsAndListeners();
        prepareQuestionList();
        setNextQuestion();
    }

    private void setupViewsAndListeners() {
        mQuestionModelHashMap = new HashMap<>();
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(this);
        mQuestionTextView = (TextView) findViewById(R.id.question_text);
        mRadioButtonTrue = (RadioButton) findViewById(R.id.true_click);
        mRadioButtonFalse = (RadioButton) findViewById(R.id.false_click);
        mScoreTextView = (TextView) findViewById(R.id.score_text);
        mTimerTextView = (TextView) findViewById(R.id.timer_text);
        mRadioGroup = (RadioGroup) findViewById(R.id.true_false_radio_group);
        mThumbImageView = (ImageView) findViewById(R.id.image_on_success);
        mCurrentQuestionIndex = 0;
        mScore = 0;

        // Animations
        mFlipVerticalAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_vertical);
        mFlipHorizontalAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_horizontal);
        mFlipVerticalAnimation.setTarget(mQuestionTextView);
        mFlipHorizontalAnimation.setTarget(mRadioGroup);

    }

    private void setTimer() {
        mRemainingSeconds = 10;
        mCountDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerTextView.setText((mRemainingSeconds--) + " seconds");
            }

            @Override
            public void onFinish() {
                setNextQuestion();
            }
        };
        mCountDownTimer.start();
    }

    private void prepareQuestionList() {
        String file = getTextFileContents();
        String[] questionArray = file.split("\\r?\\n");
        for (int i = 0; i < questionArray.length; i++) {
            QuestionModel questionModel = new QuestionModel();
            String[] quesAnsArr = questionArray[i].split("\\|");
            questionModel.question = quesAnsArr[0];
            questionModel.answer = quesAnsArr[1];
            questionModel.option1 = "True";
            questionModel.option2 = "False";
            mQuestionModelHashMap.put(i, questionModel);
        }
        mShuffledQuestionNumbers = Utility.uniqueRandomNumbers(mQuestionModelHashMap.size());
    }

    private void setNextQuestion() {
        //Check previous answer.
        String currentAnswer = "";
        if (mRadioButtonTrue.isChecked()) {
            currentAnswer = mRadioButtonTrue.getText().toString().trim();
        } else if (mRadioButtonFalse.isChecked()) {
            currentAnswer = mRadioButtonFalse.getText().toString().trim();
        }
        if (mCurrentQuestionModel != null && mCurrentQuestionModel.answer.equalsIgnoreCase(currentAnswer)) {
            mScore = mScore + 10;
            mScoreTextView.setText(String.valueOf(mScore));
            animateThumbsImage(R.drawable.thumbs_up);
        } else if (mCurrentQuestionModel != null && !mCurrentQuestionModel.answer.equalsIgnoreCase(currentAnswer)) {
            animateThumbsImage(R.drawable.thumbs_down);
        }
        //Set next question.
        setTimer();
        mRadioGroup.clearCheck();
        mFlipVerticalAnimation.start();
        mFlipHorizontalAnimation.start();
        if (mCurrentQuestionIndex < mQuestionModelHashMap.size())
            mCurrentQuestionModel = mQuestionModelHashMap.get(mShuffledQuestionNumbers.get(mCurrentQuestionIndex++));
        else {
            UserModel userModel = Utility.getUserModelFromSharedPreferences(Level_1.this);
            userModel = Utility.updateScore(1, mScore, userModel);
            Utility.saveScoreToFirebase(Level_1.this, userModel);
            finish();
        }
        mQuestionTextView.setText(mCurrentQuestionModel.question);
    }

    private String getTextFileContents() {
        InputStream inputStream = getResources().openRawResource(R.raw.qa_bollywood);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }

    public void animateThumbsImage(int drawableId) {
        mThumbImageView.setVisibility(ImageView.VISIBLE);
        mThumbImageView.setImageDrawable(getResources().getDrawable(drawableId));
        AnimatorSet animatorSetForThumb;
        animatorSetForThumb = (AnimatorSet) AnimatorInflater.loadAnimator(Level_1.this, R.animator.flip_vertical);
        animatorSetForThumb.setTarget(mThumbImageView);
        animatorSetForThumb.setDuration(500);
        animatorSetForThumb.start();
        new CountDownTimer(1200, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                mThumbImageView.setVisibility(ImageView.GONE);
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:
                mCountDownTimer.cancel();
                setNextQuestion();
                break;
        }
    }
}
