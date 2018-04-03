package com.example.android.languagedetection.di.module;

import com.example.android.languagedetection.model.network.LanguageApi;
import com.example.android.languagedetection.model.repository.NewTextRepository;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class ApiModule {
    private static final String BASE_API_URL = "https://gateway-a.watsonplatform.net/";

    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder){
        return builder
                .baseUrl(BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    LanguageApi provideLanguageApi(Retrofit retrofit){
        return retrofit.create(LanguageApi.class);
    }


}
