package com.aripratom.aplikasimanajemenaset;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MyApp extends Application {
    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
    public static synchronized MyApp getInstance() {
        return mInstance;
    }
}
