package com.svs.myprojects.bollywoodquiz.models;

/**
 * Created by snehalsutar on 4/24/16.
 */
public class UserModel {
    private String userID;
    private String fullName;
    private String password;
    private ScoreModel score;

    public UserModel(String userID, String fullName, String password, ScoreModel score) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.score = score;
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
