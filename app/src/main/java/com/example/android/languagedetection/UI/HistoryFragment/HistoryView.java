package com.example.android.languagedetection.UI.HistoryFragment;

import com.example.android.languagedetection.database.History;

import java.util.List;

/**
 * Created by User on 15:12 26.03.2018.
 */

public interface HistoryView {
    void showHistory(List<History> history);
}
