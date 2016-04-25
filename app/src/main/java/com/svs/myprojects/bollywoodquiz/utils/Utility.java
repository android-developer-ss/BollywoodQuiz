package com.svs.myprojects.bollywoodquiz.utils;

import java.util.ArrayList;
import java.util.Collections;

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

    public static void saveScore(){
//       Firebase ref = new Firebase(Constants.FIREBASE_USERS_URL);
//        UserModel userModel = new UserModel();
//        ref.setValue(userModel);
    }

    public static void storeUserModelToSharedPreferences(){
//some more lines
    }
}
