package com.example.android.languagedetection.di.component;

import com.example.android.languagedetection.ui.historyFragment.HistoryFragment;
import com.example.android.languagedetection.di.module.HistoryFragmentModule;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Created by User on 15:48 27.03.2018.
 */
//
//@Singleton
//@Subcomponent(modules = {HistoryFragmentModule.class})
public interface HistoryFragmentComponent {
    void inject(HistoryFragment historyFragment);
}