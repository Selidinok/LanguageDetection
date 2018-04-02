package com.example.android.languagedetection.di.module;

import com.example.android.languagedetection.ui.newTextFragment.NewTextPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 15:39 27.03.2018.
 */

@Module
public class NewTextFragmentModule {

    @Singleton
    @Provides
    NewTextPresenter provideNewTextPresenter() {
        return new NewTextPresenter();
    }
}
