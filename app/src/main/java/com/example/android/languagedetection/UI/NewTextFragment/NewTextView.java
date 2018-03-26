package com.example.android.languagedetection.UI.NewTextFragment;

import android.app.Dialog;

/**
 * Created by User on 15:32 26.03.2018.
 */

public interface NewTextView {
    String getText();

    void showEmptyError();

    void showLoading();

    void hideLoading();

    Dialog openDialog(String language);

    void setDialog(Dialog dialog, String language);
}
