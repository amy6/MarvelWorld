package com.example.marvelworld.network

import com.example.marvelworld.model.MarvelApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("v1/public/characters")
    fun getMarvelCharacters(@Query("ts") timestamp: Long,
                            @Query("apikey") apiKey: String?,
                            @Query("hash") hash: String?): Call<MarvelApiResponse?>?

    @GET("v1/public/characters/{id}")
    fun getMarvelCharacter(@Path("id") characterId: Int,
                           @Query("ts") timestamp: Long,
                           @Query("apikey") apiKey: String?,
                           @Query("hash") hash: String?): Call<MarvelApiResponse?>?
}