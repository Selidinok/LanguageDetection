package com.example.android.languagedetection.presentation.views;

import android.app.Dialog;
import android.content.DialogInterface;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by User on 15:32 26.03.2018.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface NewTextView extends BaseView{
//    String getText();

    void showLoading();

    void hideLoading();

    void openDialog(String language);

    void cancelDialog();


//    void setDialog(Dialog dialog, String language);
}
