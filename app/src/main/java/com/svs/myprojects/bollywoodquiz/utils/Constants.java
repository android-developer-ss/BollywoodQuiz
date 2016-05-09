package com.svs.myprojects.bollywoodquiz.utils;

import java.util.ArrayList;

/**
 * Created by Snehal on 4/9/16.
 */
public class Constants {
    public static final int LEVEL_2_TIME_IN_SECS = 20;
    public static final String FIREBASE_USERS_URL = "https://bollywood-quiz.firebaseio.com/users";
    public static final String LEVEL = "level";
    public static final String LEVEL_ONE = "level_one";
    public static final String LEVEL_TWO = "level_two";
    public static final String LEVEL_THREE = "level_three";
    public static final String OFFLINE_LEVEL_INTER = "intermediate";
    public static final String OFFLINE_LEVEL_EXPERT = "expert";
    public static final String SHARED_PREF_USERS_OBJ = "SHARED_PREF_USERS_OBJ";
    public static final String USERS_OBJ = "USERS_OBJ";
    public static final int MAX_LEVEL = 6;

    private static ArrayList<String> levelContentList;
    //    = (ArrayList<String>)
//            Arrays.asList("Offline",
//                    "Offline",
//                    "Offline",
//                    "Offline",
//                    "Offline",
//                    "Online");
    private static ArrayList<String> levelDescriptionList;
//    = (ArrayList<String>)
//            Arrays.asList(
//                    "Simple True and False text questions",
//                    "Text Questions with multiple choices",
//                    "Text Questions with multiple choices",
//                    "Images without faces.. Guess the movie name",
//                    "We'll play the audio song.. Guess the movie name",
//                    "We'll play the video song without audio.. Guess the movie name"
//            );

    public static void setLevelContentList(ArrayList<String> levelContentList) {
        Constants.levelContentList = levelContentList;
    }

    public static void setLevelDescriptionList(ArrayList<String> descriptionList) {
        Constants.levelDescriptionList = descriptionList;
    }

    public static String getLevelContent(int position) {
        return levelContentList.get(position);
    }

    public static String getLevelDescription(int position) {
        return levelDescriptionList.get(position);
    }

}
