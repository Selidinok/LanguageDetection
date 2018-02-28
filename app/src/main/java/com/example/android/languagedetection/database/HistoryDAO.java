package com.example.android.languagedetection.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by User on 11:49 28.02.2018.
 */

@Dao
public interface HistoryDAO {

    @Insert
    void add(History history);

    @Update
    void update(History history);

    @Delete
    void delete(History history);

    @Query("SELECT * FROM history")
    Flowable<List<History>> getAll();
}
