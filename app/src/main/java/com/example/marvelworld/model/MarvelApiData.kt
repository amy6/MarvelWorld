package com.example.marvelworld.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MarvelApiData {
    @SerializedName("offset")
    @Expose
    var offset: Int? = null
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("results")
    @Expose
    var marvelCharacters: List<MarvelCharacter>? = null
        private set

    fun setResults(marvelCharacters: List<MarvelCharacter>?) {
        this.marvelCharacters = marvelCharacters
    }
}