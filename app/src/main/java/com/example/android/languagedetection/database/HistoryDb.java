package com.example.android.languagedetection.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by User on 12:04 28.02.2018.
 */

@Database(entities = {History.class}, version = 1)
public abstract class HistoryDb extends RoomDatabase {
    public abstract HistoryDAO getHistoryDao();
}
