package com.example.marvelworld.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.marvelworld.data.MarvelDataRepository
import com.example.marvelworld.model.MarvelCharacter

class MarvelListViewModel(apiKey: String?) : ViewModel() {
    val marvelCharacters: LiveData<List<MarvelCharacter>>
    private val repository: MarvelDataRepository = MarvelDataRepository(apiKey!!)

    init {
        marvelCharacters = repository.getMarvelCharacters()
    }
}