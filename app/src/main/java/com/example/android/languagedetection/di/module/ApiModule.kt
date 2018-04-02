package com.example.android.languagedetection.di.module

import com.example.android.languagedetection.network.LanguageApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {
    companion object {
        private const val BASE_API_QUALIFIER = "BASE_API_QUALIFIER"
        private const val BASE_API_URL = "https://gateway-a.watsonplatform.net/"
    }

    @Provides
    @Named(BASE_API_QUALIFIER)
    @Singleton
    fun provideBaseRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit {
        return retrofitBuilder
                .baseUrl(BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideLanguageApi(@Named(BASE_API_QUALIFIER) retrofit: Retrofit): LanguageApi {
        return retrofit.create(LanguageApi::class.java)
    }
}