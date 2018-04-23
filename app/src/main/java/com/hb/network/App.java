package com.hb.network;

import android.app.Application;
import android.content.Context;

import com.hb.base.utils.T;

public class App extends Application {
    private static App instance;
    public static String token = "";

    @Override
    public void onCreate() {
        super.onCreate();
        T.init(this);
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

}
