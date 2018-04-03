package com.example.android.languagedetection.presentation.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.android.languagedetection.model.database.History;

import java.util.List;

/**
 * Created by User on 15:12 26.03.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface HistoryView extends BaseView {
    void showHistory(List<History> history);
}
