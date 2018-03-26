package com.example.android.languagedetection.UI.HistoryFragment;

import com.example.android.languagedetection.database.DatabaseModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 14:15 26.03.2018.
 */

public class HistoryPresenter {
    private HistoryView historyView;

    public HistoryPresenter(HistoryView historyView) {
        this.historyView = historyView;
    }

    public void detachHistoryView() {
        historyView = null;
    }

    public HistoryView getHistoryView() {
        return historyView;
    }

    public void loadHistory() {
        DatabaseModel.loadHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(history -> historyView.showHistory(history));
    }
}
