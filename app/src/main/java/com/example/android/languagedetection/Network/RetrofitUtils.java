package com.example.android.languagedetection.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 15:05 01.03.2018.
 */

public class RetrofitUtils {
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static OkHttpClient createClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return client;
    }


    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway-a.watsonplatform.net/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(createClient())
                .build();
        return retrofit;
    }
}
