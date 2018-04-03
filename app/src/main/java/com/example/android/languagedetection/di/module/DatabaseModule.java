package com.example.android.languagedetection.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.android.languagedetection.model.database.HistoryDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    private Context context;

    public DatabaseModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    HistoryDb provideHistoryDb(){
        return  Room.databaseBuilder(context,
                HistoryDb.class, "history").build();
    }
}
