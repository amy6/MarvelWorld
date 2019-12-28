package com.example.marvelworld.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarvelApiClient {
    private const val BASE_URL = "https://gateway.marvel.com/"
    private var retrofit: Retrofit? = null
    private val httpClient = OkHttpClient.Builder()
    private val loggingInterceptor = HttpLoggingInterceptor()
    @JvmStatic
    val client: Retrofit?
        get() {
            if (retrofit == null) {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(loggingInterceptor)
                retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .baseUrl(BASE_URL)
                        .build()
            }
            return retrofit
        }
}