package com.example.marvelworld.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MarvelCharacter() : Parcelable {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("modified")
    @Expose
    var modified: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null
    @SerializedName("comics")
    @Expose
    var comics: Comics? = null
    @SerializedName("series")
    @Expose
    var series: Series? = null
    @SerializedName("stories")
    @Expose
    var stories: Stories? = null
    @SerializedName("events")
    @Expose
    var events: Events? = null
    @SerializedName("urls")
    @Expose
    var urls: List<Url>? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        description = parcel.readString()
        modified = parcel.readString()
        resourceURI = parcel.readString()
    }

    override fun equals(other: Any?): Boolean {
        if (other is MarvelCharacter) {
            return id == other.id && name == other.name && description == other.description && modified == other.modified
        }
        return false
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(modified)
        parcel.writeString(resourceURI)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MarvelCharacter> {
        override fun createFromParcel(parcel: Parcel): MarvelCharacter {
            return MarvelCharacter(parcel)
        }

        override fun newArray(size: Int): Array<MarvelCharacter?> {
            return arrayOfNulls(size)
        }
    }
}