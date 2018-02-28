package com.example.android.languagedetection.database;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by User on 12:20 28.02.2018.
 */

//Синглетон, для того чтобы не было копий БД
public class DatabaseCreator {

    private static HistoryDb historyDb;
    private static final Object LOCK = new Object();

    public synchronized static HistoryDb getPersonDatabase(Context context){
        if(historyDb == null) {
            synchronized (LOCK) {
                if (historyDb == null) {
                    historyDb = Room.databaseBuilder(context,
                            HistoryDb.class, "history").build();
                }
            }
        }
        return historyDb;
    }
}
