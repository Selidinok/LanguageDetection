package com.example.android.languagedetection.ui.HistoryFragment.dagger;

import com.example.android.languagedetection.ui.HistoryFragment.HistoryFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Created by User on 15:48 27.03.2018.
 */

@Singleton
@Subcomponent(modules = {HistoryFragmentModule.class})
public interface HistoryFragmentComponent {
    void inject(HistoryFragment historyFragment);
}