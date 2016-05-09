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
import com.svs.myprojects.bollywoodquiz.utils.Constants;
import com.svs.myprojects.bollywoodquiz.utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Level_2 extends AppCompatActivity implements View.OnClickListener {

    public static String LOG_TAG = Level_2.class.getSimpleName();
    private HashMap<Integer, QuestionModel> mQuestionModelHashMap;
    private QuestionModel mCurrentQuestionModel;
    private Button mNextButton;
    private RadioButton mOption1, mOption2, mOption3, mOption4;
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
    private String mLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_2_activity);

        setupViewsAndListeners();
        prepareQuestionList();
        setNextQuestion();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();
    }

    private void setupViewsAndListeners() {
        mLevel = getIntent().getStringExtra(Constants.LEVEL);
        mQuestionModelHashMap = new HashMap<>();
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(this);
        mQuestionTextView = (TextView) findViewById(R.id.question_text);
        mOption1 = (RadioButton) findViewById(R.id.option1);
        mOption2 = (RadioButton) findViewById(R.id.option2);
        mOption3 = (RadioButton) findViewById(R.id.option3);
        mOption4 = (RadioButton) findViewById(R.id.option4);
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
        mRemainingSeconds = Constants.LEVEL_2_TIME_IN_SECS;
        mCountDownTimer = new CountDownTimer(Constants.LEVEL_2_TIME_IN_SECS * 1000, 1000) {
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
            questionModel.option1 = quesAnsArr[1];
            questionModel.option2 = quesAnsArr[2];
            questionModel.option3 = quesAnsArr[3];
            questionModel.option4 = quesAnsArr[4];
            questionModel.answer = quesAnsArr[5];

            mQuestionModelHashMap.put(i, questionModel);
        }
        mShuffledQuestionNumbers = Utility.uniqueRandomNumbers(mQuestionModelHashMap.size());
    }

    private void setNextQuestion() {
        //Check previous answer.
        String currentAnswer = "";
        if (mOption1.isChecked()) {
            currentAnswer = mOption1.getText().toString().trim();
        } else if (mOption2.isChecked()) {
            currentAnswer = mOption2.getText().toString().trim();
        } else if (mOption3.isChecked()) {
            currentAnswer = mOption3.getText().toString().trim();
        } else if (mOption4.isChecked()) {
            currentAnswer = mOption4.getText().toString().trim();
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
            animateWellDone(R.drawable.im_well_done_smiley);
        }
        mQuestionTextView.setText(mCurrentQuestionModel.question);
        mOption1.setText(mCurrentQuestionModel.option1);
        mOption2.setText(mCurrentQuestionModel.option2);
        mOption3.setText(mCurrentQuestionModel.option3);
        mOption4.setText(mCurrentQuestionModel.option4);
    }

    private String getTextFileContents() {
        InputStream inputStream;
        if (mLevel.equals(Constants.OFFLINE_LEVEL_INTER)) {
            inputStream = getResources().openRawResource(R.raw.qa_bollywood_intermediate);
        } else {
            inputStream = getResources().openRawResource(R.raw.qa_bollywood_expert);
        }
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

    /***********************************************************************************************
     * Animation for questions and well done smiley.
     */
    public void animateThumbsImage(int drawableId) {
        mThumbImageView.setVisibility(ImageView.VISIBLE);
        mThumbImageView.setImageDrawable(getResources().getDrawable(drawableId));
        AnimatorSet animatorSetForThumb;
        animatorSetForThumb = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_vertical);
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

    public void animateWellDone(int drawableId) {
        mNextButton.setVisibility(View.INVISIBLE);
        mThumbImageView.setVisibility(View.VISIBLE);
        mThumbImageView.setMinimumHeight((int) getResources().getDimension(R.dimen.well_done_size));
        mThumbImageView.setMinimumWidth((int) getResources().getDimension(R.dimen.well_done_size));
        mThumbImageView.setImageDrawable(getResources().getDrawable(drawableId));
        AnimatorSet animatorSetForThumb;
        animatorSetForThumb = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_vertical);
        animatorSetForThumb.setTarget(mThumbImageView);
        animatorSetForThumb.setDuration(1000);
        animatorSetForThumb.start();
        new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                UserModel userModel = Utility.getUserModelFromSharedPreferences(Level_2.this);
                if (mLevel.equals(Constants.OFFLINE_LEVEL_INTER))
                    userModel = Utility.updateScore(2, mScore, userModel); //scoreModel.setOffline_level_2(mScore);
                else if ((mLevel.equals(Constants.OFFLINE_LEVEL_EXPERT)))
                    userModel = Utility.updateScore(3, mScore, userModel);//scoreModel.setOffline_level_3(mScore);
                Utility.saveScoreToFirebase(Level_2.this, userModel);
                finish();
            }
        }.start();
    }

    /***********************************************************************************************
     * On Click Listeners..
     */
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
