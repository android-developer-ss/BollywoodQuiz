package com.svs.myprojects.bollywoodquiz;

import android.app.Application;

import com.firebase.client.Firebase;
import com.svs.myprojects.bollywoodquiz.utils.Constants;

import java.util.ArrayList;


/**
 * Created by Snehal on 4/20/16.
 */
public class BollywoodApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        ArrayList<String> levelContentList = new ArrayList<>();
        levelContentList.add("Offline");
        levelContentList.add("Offline");
        levelContentList.add("Offline");
        levelContentList.add("Offline");
        levelContentList.add("Offline");
        levelContentList.add("Online");

        Constants.setLevelContentList(levelContentList);

        ArrayList<String> levelDescriptionList = new ArrayList<>();
        levelDescriptionList.add("Simple True and False text questions");
        levelDescriptionList.add("Text Questions with multiple choices");
        levelDescriptionList.add("Text Questions with multiple choices");
        levelDescriptionList.add("Images without faces.. Guess the movie name....");
        levelDescriptionList.add("We'll play the audio song.. Guess the movie name...");
        levelDescriptionList.add("We'll play the video song without audio.. Guess the movie name...");

        Constants.setLevelDescriptionList(levelDescriptionList);
    }
}
