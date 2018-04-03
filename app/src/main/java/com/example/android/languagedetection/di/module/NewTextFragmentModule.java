package com.example.android.languagedetection.di.module;

import com.example.android.languagedetection.model.network.LanguageApi;
import com.example.android.languagedetection.presentation.presenters.NewTextPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 15:39 27.03.2018.
 */

@Module
public class NewTextFragmentModule {

//    private LanguageApi languageApi;
//
//    private Router router;
//
//    public NewTextFragmentModule(LanguageApi languageApi, Router router) {
//        this.languageApi = languageApi;
//        this.router = router;
//    }

    @Singleton
    @Provides
    NewTextPresenter provideNewTextPresenter() {
        return null;
    }

//    @Provides
//    public abstract NewTextPresenter provideNewTextPresenter();
}
