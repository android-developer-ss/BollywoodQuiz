package com.svs.myprojects.bollywoodquiz.models;

import android.content.Context;

import com.svs.myprojects.bollywoodquiz.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Snehal on 4/25/16.
 */
public class UserModel {
    private String userID;
    private String fullName;
    private String password;
    private ScoreModel score;
    private ArrayList<ScoreModel> scoreModelArrayList;

    public UserModel(String userID, String fullName, String password, Context context) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        scoreModelArrayList = new ArrayList<>();
        for (int i = 0; i < Constants.MAX_LEVEL; i++) {
            ScoreModel scoreModel = new ScoreModel();
            scoreModel.setLevel(i + 1);
        }
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ScoreModel getScore() {
        return score;
    }

    public void setScore(ScoreModel score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userID='" + userID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                '}';
    }
}