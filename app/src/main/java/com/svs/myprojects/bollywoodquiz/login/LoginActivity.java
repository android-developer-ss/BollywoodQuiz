package com.svs.myprojects.bollywoodquiz.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.svs.myprojects.bollywoodquiz.R;

public class LoginActivity extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://android-chat.firebaseio-demo.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
