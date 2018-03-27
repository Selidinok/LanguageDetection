package com.example.android.languagedetection.app;

import android.app.Application;

/**
 * Created by User on 15:53 27.03.2018.
 */

public class App extends Application {
    private static App instance;

    private AppComponent appComponent;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
