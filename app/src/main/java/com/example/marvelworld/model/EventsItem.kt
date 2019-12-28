package com.example.marvelworld.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventsItem {
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}