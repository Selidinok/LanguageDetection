package com.example.android.languagedetection.app;

import com.example.android.languagedetection.ui.HistoryFragment.dagger.HistoryFragmentComponent;
import com.example.android.languagedetection.ui.NewTextFragment.dagger.NewTextFragmentComponent;

import dagger.Component;

/**
 * Created by User on 16:05 27.03.2018.
 */

@Component
public interface AppComponent {
    HistoryFragmentComponent createHistoryFragmentComponent();

    NewTextFragmentComponent createNewTextFragmentComponent();
}
