package com.example.marvelworld.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelworld.BuildConfig
import com.example.marvelworld.model.MarvelApiResponse
import com.example.marvelworld.model.MarvelCharacter
import com.example.marvelworld.network.MarvelApiClient.client
import com.example.marvelworld.network.MarvelApiService
import com.example.marvelworld.ui.MarvelListActivity
import com.example.marvelworld.utils.DigestUtils.getMd5
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MarvelDataRepository(private val apiKey: String) {
    private val marvelCharacters: MutableLiveData<List<MarvelCharacter>> = MutableLiveData()
    private val marvelApiService: MarvelApiService = client!!.create(MarvelApiService::class.java)

    fun getMarvelCharacters(): LiveData<List<MarvelCharacter>> {

        val ts = Date().time
        val hash = getMd5(ts, MARVEL_API_KEY_PRIVATE, apiKey)

        marvelApiService.getMarvelCharacters(ts, apiKey, hash)!!.enqueue(object : Callback<MarvelApiResponse?> {
            override fun onResponse(call: Call<MarvelApiResponse?>, response: Response<MarvelApiResponse?>) {
                Log.d(MarvelListActivity.TAG, "API call successful.")
                if (response.body() != null && response.body()!!.marvelApiData?.marvelCharacters != null && response.body()!!.marvelApiData?.marvelCharacters?.isNotEmpty()!!) {
                    Log.d(MarvelListActivity.TAG, "Characters fetched : " + response.body()!!.marvelApiData?.marvelCharacters?.size)
                    marvelCharacters.postValue(response.body()!!.marvelApiData?.marvelCharacters)
                }
            }

            override fun onFailure(call: Call<MarvelApiResponse?>, t: Throwable) {
                Log.e(MarvelListActivity.TAG, "API call failed with error : " + t.message)
            }
        })

        return marvelCharacters
    }

    fun getMarvelCharacter(id: Int): LiveData<MarvelCharacter> {
        val ts = Date().time
        val hash = getMd5(ts, MARVEL_API_KEY_PRIVATE, apiKey)

        val marvelCharacter = MutableLiveData<MarvelCharacter>()

        marvelApiService.getMarvelCharacter(id, ts, apiKey, hash)!!.enqueue(object : Callback<MarvelApiResponse?> {
            override fun onResponse(call: Call<MarvelApiResponse?>, response: Response<MarvelApiResponse?>) {
                Log.d(MarvelListActivity.TAG, "API call successful.")
                if (response.body() != null) {
                    val character = response.body()!!.marvelApiData?.marvelCharacters?.get(0)
                    Log.d(MarvelListActivity.TAG, "Details obtained for character : " + character?.name)
                    marvelCharacter.value = character
                }
            }

            override fun onFailure(call: Call<MarvelApiResponse?>, t: Throwable) {
                Log.e(MarvelListActivity.TAG, "API call failed with error : " + t.message)
            }
        })

        return marvelCharacter
    }

    companion object {
        const val MARVEL_API_KEY_PRIVATE = BuildConfig.MARVEL_API_KEY_PRIVATE
    }

}