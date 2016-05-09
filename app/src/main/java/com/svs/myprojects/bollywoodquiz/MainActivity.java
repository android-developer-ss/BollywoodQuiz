package com.svs.myprojects.bollywoodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.svs.myprojects.bollywoodquiz.offline.Level_1;
import com.svs.myprojects.bollywoodquiz.offline.Level_2;
import com.svs.myprojects.bollywoodquiz.offline.Level_4_Image;
import com.svs.myprojects.bollywoodquiz.offline.Level_5_Audio;
import com.svs.myprojects.bollywoodquiz.online.VideoRound;
import com.svs.myprojects.bollywoodquiz.playboard.PlayBoardActivity;
import com.svs.myprojects.bollywoodquiz.utils.Constants;
import com.svs.myprojects.bollywoodquiz.views.LevelButtonView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mPlayBoardButton;
    private LevelButtonView mOfflineLevel_1_Button;
    private LevelButtonView mOfflineLevel_2_Button;
    private LevelButtonView mOfflineLevel_3_Button;
    private LevelButtonView mOnlineLevel_4_Button;
    private LevelButtonView mOnlineLevel_5_Button;
    private LevelButtonView mOnlineLevel_6_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
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

        setupViews();

    }

    private void setupViews() {
        mPlayBoardButton = (Button) findViewById(R.id.play_board_button);
        mOfflineLevel_1_Button = (LevelButtonView) findViewById(R.id.level_1_offline);
        mOfflineLevel_2_Button = (LevelButtonView) findViewById(R.id.level_2_offline);
        mOfflineLevel_3_Button = (LevelButtonView) findViewById(R.id.level_3_offline);
        mOnlineLevel_4_Button = (LevelButtonView) findViewById(R.id.level_4_image);
        mOnlineLevel_5_Button = (LevelButtonView) findViewById(R.id.level_5_audio);
        mOnlineLevel_6_Button = (LevelButtonView) findViewById(R.id.level_6_video);
        mPlayBoardButton.setOnClickListener(this);
        mOfflineLevel_1_Button.setOnClickListener(this);
        mOfflineLevel_2_Button.setOnClickListener(this);
        mOfflineLevel_3_Button.setOnClickListener(this);
        mOnlineLevel_4_Button.setOnClickListener(this);
        mOnlineLevel_5_Button.setOnClickListener(this);
        mOnlineLevel_6_Button.setOnClickListener(this);

        //Set Button Content Text
        mOfflineLevel_1_Button.setLevelContentText(Constants.getLevelContent(0));
        mOfflineLevel_2_Button.setLevelContentText(Constants.getLevelContent(1));
        mOfflineLevel_3_Button.setLevelContentText(Constants.getLevelContent(2));
        mOnlineLevel_4_Button.setLevelContentText(Constants.getLevelContent(3));
        mOnlineLevel_5_Button.setLevelContentText(Constants.getLevelContent(4));
        mOnlineLevel_6_Button.setLevelContentText(Constants.getLevelContent(5));
        //Set Button Description Text
        mOfflineLevel_1_Button.setLevelDescriptionText(Constants.getLevelDescription(0));
        mOfflineLevel_2_Button.setLevelDescriptionText(Constants.getLevelDescription(1));
        mOfflineLevel_3_Button.setLevelDescriptionText(Constants.getLevelDescription(2));
        mOnlineLevel_4_Button.setLevelDescriptionText(Constants.getLevelDescription(3));
        mOnlineLevel_5_Button.setLevelDescriptionText(Constants.getLevelDescription(4));
        mOnlineLevel_6_Button.setLevelDescriptionText(Constants.getLevelDescription(5));

    }

//    public void play_offline_function(View view) {
//        Intent intent = new Intent(MainActivity.this, OfflineQuizMainPage.class);
//        startActivity(intent);
//    }
//
//    public void play_online_function(View view) {
//        Intent intent = new Intent(MainActivity.this, VideoRound.class);
//        startActivity(intent);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_board_button:
                finish();
                startIntent(PlayBoardActivity.class, null);
                break;
            case R.id.level_1_offline:
                startIntent(Level_1.class, null);
                break;
            case R.id.level_2_offline:
                startIntent(Level_2.class, Constants.OFFLINE_LEVEL_INTER);
                break;
            case R.id.level_3_offline:
                startIntent(Level_2.class, Constants.OFFLINE_LEVEL_EXPERT);
                break;
            case R.id.level_4_image:
                startIntent(Level_4_Image.class, null);
                break;
            case R.id.level_5_audio:
                startIntent(Level_5_Audio.class, null);
                break;
            case R.id.level_6_video:
                startIntent(VideoRound.class, null);
                break;
        }
    }

    private void startIntent(Class activityClass, String level) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        if (level != null) {
            intent.putExtra(Constants.LEVEL, level);
        }
        startActivity(intent);
    }
}
