package com.example.marvelworld.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.marvelworld.data.MarvelDataRepository
import com.example.marvelworld.model.MarvelCharacter

class MarvelDetailViewModel(apiKey: String?, private val id: Int) : ViewModel() {

    private var marvelCharacter: LiveData<MarvelCharacter>? = null
    private val repository: MarvelDataRepository = MarvelDataRepository(apiKey!!)

    fun getMarvelCharacter(): LiveData<MarvelCharacter> {
        marvelCharacter = repository.getMarvelCharacter(id)
        return marvelCharacter!!
    }

}