package com.example.denis.vjetgrouptestapp;

import android.app.Application;

import com.facebook.FacebookSdk;

import io.realm.Realm;

public class App extends Application {

    private final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        FacebookSdk.sdkInitialize(this);
    }
}
