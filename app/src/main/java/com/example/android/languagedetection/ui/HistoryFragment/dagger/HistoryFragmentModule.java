package com.example.android.languagedetection.ui.HistoryFragment.dagger;

import com.example.android.languagedetection.ui.HistoryFragment.HistoryPresenter;
import com.example.android.languagedetection.ui.HistoryFragment.MyHistoryRecyclerViewAdapter;

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
