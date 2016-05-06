package com.svs.myprojects.bollywoodquiz.models;

import java.util.HashMap;

/**
 * Created by Snehal on 4/25/16.
 */
public class UserModel {
    public String userID;
    public String fullName;
    public String password;
    //    private ScoreModel score;
    public HashMap<Integer, Integer> scoreHashMap;

    public UserModel(String userID, String fullName, String password) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.scoreHashMap = new HashMap<>();
    }

//
//    public UserModel(String userID, String fullName, String password) {
//        this.userID = userID;
//        this.fullName = fullName;
//        this.password = password;
//        scoreHashMap = new HashMap<>();
////        for (int i = 0; i < Constants.MAX_LEVEL; i++) {
////            ScoreModel scoreModel = new ScoreModel();
////            scoreModel.setLevel(i + 1);
////        }
//    }

//    public String getUserID() {
//        return userID;
//    }
//
//    public void setUserID(String userID) {
//        this.userID = userID;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public ScoreModel getScore() {
//        return score;
//    }
//
//    public void setScore(ScoreModel score) {
//        this.score = score;
//    }
//
//    @Override
//    public String toString() {
//        return "UserModel{" +
//                "userID='" + userID + '\'' +
//                ", fullName='" + fullName + '\'' +
//                ", password='" + password + '\'' +
//                ", score=" + score +
//                '}';
//    }
}