package com.example.android.languagedetection.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.languagedetection.model.interceptor.HistoryInteraptor;
import com.example.android.languagedetection.presentation.views.HistoryView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by User on 14:15 26.03.2018.
 */

@InjectViewState
public class HistoryPresenter extends BasePresenter<HistoryView> {

    private HistoryInteraptor historyInteraptor;

    @Inject
    public HistoryPresenter(HistoryInteraptor historyInteraptor) {
        this.historyInteraptor = historyInteraptor;
    }

    public void loadHistory() {
        Disposable disposable = historyInteraptor.getHistory()
                .subscribe(history -> getViewState().showHistory(history));
        addToComposite(disposable);
    }
}
