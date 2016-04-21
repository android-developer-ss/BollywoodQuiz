package com.svs.myprojects.bollywoodquiz;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Snehal on 4/20/16.
 */
public class BollywoodApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
