package com.example.android.languagedetection.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by User on 17:14 27.02.2018.
 */

public class DataContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.languagedetection";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_HISTORY = "history";

    public static final class DataEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_HISTORY)
                .build();

        public static final String TABLE_NAME = "history";

        public static final String COLUMN_TEXT = "text";

        public static final String COLUMN_LANGUAGE = "language";
    }
}
