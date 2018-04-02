package com.example.android.languagedetection.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 15:05 01.03.2018.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    Converter.Factory provideConventer(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofit(OkHttpClient client, Converter.Factory converter) {
        return new Retrofit.Builder()
                .addConverterFactory(converter)
                .client(client);
    }
}
