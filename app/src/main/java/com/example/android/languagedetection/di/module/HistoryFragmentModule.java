package com.example.android.languagedetection.di.module;

import com.example.android.languagedetection.ui.historyFragment.HistoryPresenter;
import com.example.android.languagedetection.ui.historyFragment.MyHistoryRecyclerViewAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 15:28 27.03.2018.
 */

@Module
public class HistoryFragmentModule {

    @Singleton
    @Provides
    HistoryPresenter provideHistoryPresenter() {
        return new HistoryPresenter();
    }

    @Singleton
    @Provides
    MyHistoryRecyclerViewAdapter provideHistoryAdapter() {
        return new MyHistoryRecyclerViewAdapter();
    }
}
