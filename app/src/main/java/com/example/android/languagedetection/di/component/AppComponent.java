package com.example.android.languagedetection.di.component;

import com.example.android.languagedetection.di.module.ApiModule;
import com.example.android.languagedetection.di.module.ApiModule2;
import com.example.android.languagedetection.di.module.HistoryFragmentModule;
import com.example.android.languagedetection.di.module.NewTextFragmentModule;
import com.example.android.languagedetection.ui.historyFragment.HistoryFragment;
import com.example.android.languagedetection.ui.newTextFragment.NewTextFragment;
import com.example.android.languagedetection.ui.newTextFragment.NewTextPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by User on 16:05 27.03.2018.
 */
@Singleton
@Component(modules = {ApiModule2.class, HistoryFragmentModule.class, NewTextFragmentModule.class})
public interface AppComponent {
//    HistoryFragmentComponent createHistoryFragmentComponent();
//
//    NewTextFragmentComponent createNewTextFragmentComponent();
    void inject(HistoryFragment historyFragment);

    void inject(NewTextFragment newTextFragment);

    void inject(NewTextPresenter presenter);
}
