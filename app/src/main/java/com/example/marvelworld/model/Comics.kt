package com.example.marvelworld.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Comics {
    @SerializedName("available")
    @Expose
    var available: Int? = null
    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null
    @SerializedName("items")
    @Expose
    var comicsItems: List<ComicsItem>? = null
    @SerializedName("returned")
    @Expose
    var returned: Int? = null

}