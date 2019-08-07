package com.example.marvelworld.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelApiClient {

    private static final String BASE_URL = "https://gateway.marvel.com/";
    private static Retrofit retrofit;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    public static Retrofit getClient() {

        if (retrofit == null) {

            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .baseUrl(BASE_URL)
                    .build();
        }

        return retrofit;
    }
}
