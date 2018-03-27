package com.example.android.languagedetection.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 14:10 01.03.2018.
 */

/*
* Интерфейс реализующий запросы к API
* */
public interface LanguageApi {

    @GET("/calls/text/TextGetLanguage?outputMode=json")
    Single<LanguageInfo> getData(@Query("apikey") String apiKey, @Query("text") String text);


}
