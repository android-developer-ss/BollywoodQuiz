package com.svs.myprojects.bollywoodquiz.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;
import com.google.gson.GsonBuilder;
import com.svs.myprojects.bollywoodquiz.models.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Snehal on 4/5/16.
 */
public class Utility {
    public static ArrayList<Integer> uniqueRandomNumbers(int length) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        return list;
    }

    public static void saveScoreToFirebase(Context context, UserModel userModel) {
        Firebase ref = new Firebase(Constants.FIREBASE_USERS_URL);
        ref.child(userModel.userID).setValue(userModel);
        storeUserModelToSharedPreferences(context, userModel);
    }

    public static void storeUserModelToSharedPreferences(Context context, UserModel userModel) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SHARED_PREF_USERS_OBJ, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.USERS_OBJ, new GsonBuilder().create().toJson(userModel));
        editor.commit();
    }

    public static UserModel getUserModelFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_USERS_OBJ, Context.MODE_PRIVATE);
        String userModelString = sharedPreferences.getString(Constants.USERS_OBJ, null);
        UserModel userModel = new GsonBuilder().create().fromJson(userModelString, UserModel.class);
        return userModel;
    }

    public static UserModel convertStringToUserModel(String userModelString) {
        return new GsonBuilder().create().fromJson(userModelString, UserModel.class);
    }

    public static boolean ifUserIsLoggedIn(Context context) {
        if (getUserModelFromSharedPreferences(context) != null) {
            return true;
        } else
            return false;
    }

    public static void cleanSharedPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHARED_PREF_USERS_OBJ, Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }

    public static UserModel updateScore(int level, int currentScore, UserModel userModel) {
//        ScoreModel scoreModel = userModel.getScore();
//        int highestScore = scoreModel.getOffline_level_1();
//        if (highestScore < mScore) {
//            scoreModel.setOffline_level_1(mScore);
//            userModel.setScore(scoreModel);
//        }
//        HashMap<Integer, Integer> scoreHashMap = userModel.scoreHashMap;
        if (userModel.scoreHashMap == null)
            userModel.scoreHashMap = new HashMap<>();

        if (!userModel.scoreHashMap.containsKey(level)) {
            userModel.scoreHashMap.put(level, currentScore);
        } else if (currentScore > userModel.scoreHashMap.get(level)) {
            userModel.scoreHashMap.put(level, currentScore);
        }
//        updateScoreInFireBase(userModel);
        return userModel;
    }

//    private static void updateScoreInFireBase(final UserModel userModel) {
//        final Firebase ref = new Firebase(Constants.FIREBASE_USERS_URL);
//        Query queryRef = ref.orderByKey();
//        queryRef.limitToFirst(1);
//        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                ref.child(userModel.userID).setValue(userModel);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//    }
}
