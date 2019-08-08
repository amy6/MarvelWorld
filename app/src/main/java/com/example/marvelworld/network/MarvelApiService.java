package com.example.marvelworld.network;

import com.example.marvelworld.model.MarvelApiResponse;
import com.example.marvelworld.model.MarvelCharacter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarvelApiService {

    @GET("v1/public/characters")
    Call<MarvelApiResponse> getMarvelCharacters(@Query("ts") long timestamp,
                                                @Query("apikey") String apiKey,
                                                @Query("hash") String hash);

    @GET("v1/public/characters/{characterId}")
    MarvelCharacter getMarvelCharacter(@Query("ts") long timestamp,
                                       @Query("apikey") String apiKey,
                                       @Query("hash") String hash,
                                       @Path("id") int characterId);
}
