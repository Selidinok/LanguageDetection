package com.example.android.languagedetection.ui.NewTextFragment.dagger;

import com.example.android.languagedetection.ui.NewTextFragment.NewTextFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Created by User on 15:56 27.03.2018.
 */
@Singleton
@Subcomponent(modules = NewTextFragmentModule.class)
public interface NewTextFragmentComponent {
    void inject(NewTextFragment newTextFragment);
}
