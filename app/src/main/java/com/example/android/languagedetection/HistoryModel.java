package com.example.android.languagedetection;

import com.example.android.languagedetection.Network.LanguageInfo;
import com.example.android.languagedetection.database.History;
import com.example.android.languagedetection.database.HistoryDb;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 14:50 26.03.2018.
 */

public class HistoryModel {
    private final HistoryDb db;
    private LanguageInfo jsonResponse;

    public HistoryModel(HistoryDb db) {
        this.db = db;
    }

    public HistoryDb getDb() {
        return db;
    }

    public LanguageInfo getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(LanguageInfo jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    //    Добавление записи в БД
    public void insert(String text, String language) {
        final History history = new History(text, language);
        Completable.fromAction(() -> db.getHistoryDao().add(history))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public Flowable<List<History>> loadHistory() {
        return db.getHistoryDao().getAll();
    }
}
