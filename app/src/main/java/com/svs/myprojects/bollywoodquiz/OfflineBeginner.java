package com.svs.myprojects.bollywoodquiz;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;

public class OfflineBeginner extends AppCompatActivity implements AsyncTimerInterface {
    private String LOG_TAG = "svsme";

    TextView questionText, scoreText, timerText;
    static CountDownTimer mCountDownTimer ;

    Button nextButton;
    RadioButton radioButtonTrue, radioButtonFalse;
    RadioGroup radioGroup;
    ImageView imageOnSuccessOrFailure;

    int mQuestionCount;
    static int mCurrentQuestionNumber;
    String[] mQuestionArray;
    String mCurrentAnswer;
    boolean answerCorrect;
    static int mScore = 0;
    int mScoreIncreament = 10;
    int[] randomIntegerArray;
    int randomIntegerCount;

    AsyncTimer asyncTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_beginner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        asyncTimer = new AsyncTimer(this);

        createHandlers();
        readQuestionFile();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void createHandlers() {
        questionText = (TextView) findViewById(R.id.question_text);
        scoreText = (TextView) findViewById(R.id.score_text);
        timerText = (TextView) findViewById(R.id.timer_text);
        nextButton = (Button) findViewById(R.id.next_button);
        radioButtonFalse = (RadioButton) findViewById(R.id.false_click);
        radioButtonTrue = (RadioButton) findViewById(R.id.true_click);
        radioGroup = (RadioGroup) findViewById(R.id.true_false_radio_group);
        imageOnSuccessOrFailure = (ImageView) findViewById(R.id.image_on_success);

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(OfflineBeginner.this, R.anim.tween_fade);
        timerText.startAnimation(myFadeInAnimation);

    }

    private void readQuestionFile() {
        String file = readTxt();
        mQuestionArray = file.split("\\r?\\n");
        mQuestionCount = mQuestionArray.length;
        mCurrentQuestionNumber = -1;
        setRandomGenerator();
        setNextQuestion();
    }

    private void setRandomGenerator() {
        randomIntegerArray = new int[mQuestionCount];
        for(int i=0;i<mQuestionCount;i++){
            randomIntegerArray[i] = i;
        }

        Collections.shuffle(Arrays.asList(randomIntegerArray));
        randomIntegerCount=0;

    }


    private String readTxt() {
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

    public void next_button_function(View view) {
        callNextQuestion();
    }

    private void callNextQuestion() {
        checkAnswer();
        setNextQuestion();
//        radioButtonTrue.setChecked(false);
//        radioButtonFalse.setChecked(false);
        radioGroup.clearCheck();

        AnimatorSet animatorSetForQuestion;
        animatorSetForQuestion = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_vertical);
        animatorSetForQuestion.setTarget(questionText);
        animatorSetForQuestion.start();

        AnimatorSet animatorSetForRadioGroup;
        animatorSetForRadioGroup = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_horizontal);
        animatorSetForRadioGroup.setTarget(radioGroup);
        animatorSetForRadioGroup.start();


    }

    private void checkAnswer() {
//        asyncTimer.cancel(true);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        if (radioButtonID != -1) {
            RadioButton rb = (RadioButton) findViewById(radioButtonID);
            String answer = rb.getText().toString().toLowerCase();
            answer.replaceAll(" ", "");
            if (mCurrentAnswer.contains(answer)) {
                mScore = mScore + mScoreIncreament;
                scoreText.setText(" " + mScore);
                animateThumbsImage(R.drawable.thumbs_up);
//                imageOnSuccessOrFailure.setVisibility(ImageView.GONE);
            }else{
                animateThumbsImage(R.drawable.thumbs_down);
//                imageOnSuccessOrFailure.setVisibility(ImageView.VISIBLE);
//                imageOnSuccessOrFailure.setImageDrawable(getResources().getDrawable(R.drawable.thumbs_down));
//                AnimatorSet animatorSetForThumb;
//                animatorSetForThumb = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_vertical);
//                animatorSetForThumb.setTarget(imageOnSuccessOrFailure);
//                animatorSetForThumb.start();
//                imageOnSuccessOrFailure.setVisibility(ImageView.GONE);
            }
//            Toast.makeText(OfflineBeginner.this,
//                    "mAns:" + mCurrentAnswer + "      rbText:" + answer + "     mScore:" + mScore
//                            + "   mCurrentAnswer.equals(answer))"+mCurrentAnswer.equals(answer),
//                    Toast.LENGTH_LONG).show();
        }
        mCountDownTimer.cancel();

    }

    public void animateThumbsImage(int drawableId){

        imageOnSuccessOrFailure.setVisibility(ImageView.VISIBLE);
        imageOnSuccessOrFailure.setImageDrawable(getResources().getDrawable(drawableId));

        AnimatorSet animatorSetForThumb;
        animatorSetForThumb = (AnimatorSet) AnimatorInflater.loadAnimator(OfflineBeginner.this, R.animator.flip_vertical);
        animatorSetForThumb.setTarget(imageOnSuccessOrFailure);
        animatorSetForThumb.setDuration(500);
        animatorSetForThumb.start();
        new CountDownTimer(1200, 1000) {


            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                imageOnSuccessOrFailure.setVisibility(ImageView.GONE);
            }
        }.start();

    }

    private void setNextQuestion() {

//        mCurrentQuestionNumber++;
//        if(mCurrentQuestionNumber>mQuestionCount-1){
//            mCurrentQuestionNumber=0;
//        }


        mCurrentQuestionNumber = randomIntegerArray[randomIntegerCount++]; //Integer.parseInt(Arrays.toString(randomIntegerArray));
        if(randomIntegerCount>mQuestionCount){
            imageOnSuccessOrFailure.setVisibility(ImageView.VISIBLE);
        }

        String quesAns = mQuestionArray[mCurrentQuestionNumber];
        String[] quesAnsArr = quesAns.split("\\|");
        String question = quesAnsArr[0];
        mCurrentAnswer = quesAnsArr[1].toLowerCase();
        mCurrentAnswer.replaceAll(" ", "");

        questionText.setText(question);


//        asyncTimer.execute(10);
        mCountDownTimer = new CountDownTimer(11000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerText.setText(millisUntilFinished / 1000 + " secs");
            }

            public void onFinish() {
                callNextQuestion();
                timerText.setText("done!");
            }
        }.start();
    }

    @Override
    public void setText(String str) {
        timerText.setText(str + "  secs");
        if (str.equals("0")) {
            callNextQuestion();
        }
    }
}
