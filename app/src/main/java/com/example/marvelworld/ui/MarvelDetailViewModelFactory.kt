package com.example.marvelworld.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MarvelDetailViewModelFactory(private val apiKey: String, private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarvelDetailViewModel::class.java)) {
            return MarvelDetailViewModel(apiKey, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}