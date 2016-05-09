package com.svs.myprojects.bollywoodquiz.models;

import java.util.HashMap;

/**
 * Created by Snehal on 4/25/16.
 */
public class UserModel {
    public static String userIDkey = "userID";
    public static String fullNamekey = "fullName";
    public static String passwordkey = "password";
    public static String emailIDkey = "emailID";
    public static String scoreHashMapkey = "scoreHashMap";
    public String userID;
    public String fullName;
    public String password;
    public String emailID;
    public HashMap<Integer, Integer> scoreHashMap;

    public UserModel(String userID, String fullName, String password, String emailID) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.emailID = emailID;
        this.scoreHashMap = new HashMap<>();
    }

    public UserModel() {

    }



    @Override
    public String toString() {
        return "UserModel{" +
                "userID='" + userID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", scoreHashMap=" + scoreHashMap +
                '}';
    }
}