package com.example.marvelworld.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Series {
    @SerializedName("available")
    @Expose
    var available: Int? = null
    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null
    @SerializedName("items")
    @Expose
    var items: List<SeriesItem>? = null
    @SerializedName("returned")
    @Expose
    var returned: Int? = null

}