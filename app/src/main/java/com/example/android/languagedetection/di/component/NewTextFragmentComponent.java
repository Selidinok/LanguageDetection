package com.example.android.languagedetection.di.component;

import com.example.android.languagedetection.ui.newTextFragment.NewTextFragment;
import com.example.android.languagedetection.di.module.NewTextFragmentModule;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Created by User on 15:56 27.03.2018.
 */
//@Singleton
//@Subcomponent(modules = NewTextFragmentModule.class)
public interface NewTextFragmentComponent {
    void inject(NewTextFragment newTextFragment);
}
