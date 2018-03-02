package com.example.android.languagedetection.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by User on 11:45 28.02.2018.
 */

/*
* Класс реализующий структуру таблицы с историей запросов.
* Используется Room https://developer.android.com/topic/libraries/architecture/room.html
* */

@Entity
public class History {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String text;

    private String language;

    public History(String text, String language) {
        this.text = text;
        this.language = language;
    }

    @Ignore
    public History(String text) {
        this.text = text;
        this.language = "Unknown";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
