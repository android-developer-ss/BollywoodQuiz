package com.svs.myprojects.bollywoodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.svs.myprojects.bollywoodquiz.offline.Level_1;
import com.svs.myprojects.bollywoodquiz.offline.Level_2;

public class OfflineQuizMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_quiz_main_page);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void offline_beginner_function(View view){
        Intent intent= new Intent(OfflineQuizMainPage.this, Level_1.class);
        startActivity(intent);
    }
    public void offline_intermediate_function(View view){
        Intent intent= new Intent(OfflineQuizMainPage.this, Level_2.class);
        intent.putExtra(Constants.LEVEL,Constants.OFFLINE_LEVEL_INTER);
        startActivity(intent);
    }
    public void offline_expert_function(View view){
        Intent intent= new Intent(OfflineQuizMainPage.this, Level_2.class);
        intent.putExtra(Constants.LEVEL,Constants.OFFLINE_LEVEL_EXPERT);
        startActivity(intent);
    }

}
