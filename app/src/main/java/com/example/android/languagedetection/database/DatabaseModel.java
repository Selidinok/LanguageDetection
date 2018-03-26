package com.example.android.languagedetection.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 12:20 28.02.2018.
 */

//Синглетон, для того чтобы не было копий БД, а так же методы работы с ним.
public class DatabaseModel {

    private static final Object LOCK = new Object();
    private static HistoryDb historyDb;

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

    public static void insert(String text, String language) {
        final History history = new History(text, language);
        Completable.fromAction(() -> historyDb.getHistoryDao().add(history))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public static Flowable<List<History>> loadHistory() {
        return historyDb.getHistoryDao().getAll();
    }
}
