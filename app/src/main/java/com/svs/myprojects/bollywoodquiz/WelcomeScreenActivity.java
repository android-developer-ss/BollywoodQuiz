package com.svs.myprojects.bollywoodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.svs.myprojects.bollywoodquiz.login.LoginActivity;
import com.svs.myprojects.bollywoodquiz.utils.Utility;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen_activity);

        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent;
                if (Utility.ifUserIsLoggedIn(WelcomeScreenActivity.this)) {
                    intent = new Intent(WelcomeScreenActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(WelcomeScreenActivity.this, LoginActivity.class);
                }
                finish();
                startActivity(intent);
            }
        };
        countDownTimer.start();

    }

}
