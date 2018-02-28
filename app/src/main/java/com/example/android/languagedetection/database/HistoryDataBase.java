package com.example.android.languagedetection.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.languagedetection.database.DataContract.DataEntry;

/**
 * Created by User on 17:20 27.02.2018.
 */

public class HistoryDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "history.db";

    private static final int DATABASE_VERSION = 1;

    public HistoryDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_HISTORY_TABLE =

                "CREATE TABLE " + DataEntry.TABLE_NAME + " (" +

                        DataEntry._ID               + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        DataEntry.COLUMN_TEXT       + " TEXT NOT NULL, "                 +

                        DataEntry.COLUMN_LANGUAGE   + " TEXT)";

        db.execSQL(SQL_CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataEntry.TABLE_NAME);
        onCreate(db);
    }
}
