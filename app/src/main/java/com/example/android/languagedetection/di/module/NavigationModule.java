package com.example.android.languagedetection.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class NavigationModule {
    private Cicerone<Router> cicerone;

    public NavigationModule() {
        cicerone = Cicerone.create();
    }

    @Singleton
    @Provides
    Router provideRouter(){
        return cicerone.getRouter();
    }

    @Singleton
    @Provides
    NavigatorHolder provideNavigationHolder(){
        return cicerone.getNavigatorHolder();
    }

}
