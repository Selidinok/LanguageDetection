package com.example.android.languagedetection.app;

import android.app.Application;

import com.example.android.languagedetection.di.component.AppComponent;
import com.example.android.languagedetection.di.component.DaggerAppComponent;
import com.example.android.languagedetection.di.module.ApiModule;
import com.example.android.languagedetection.di.module.DatabaseModule;
import com.example.android.languagedetection.di.module.HistoryFragmentModule;
import com.example.android.languagedetection.di.module.NetworkModule;
import com.example.android.languagedetection.di.module.NewTextFragmentModule;

import ru.terrakok.cicerone.Cicerone;

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
        appComponent = DaggerAppComponent
                .builder()
                .databaseModule(new DatabaseModule(this))
                .build();
//                .create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
