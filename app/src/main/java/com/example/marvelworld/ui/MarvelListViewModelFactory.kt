package com.example.marvelworld.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MarvelListViewModelFactory(private val apiKey: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarvelListViewModel::class.java)) {
            return MarvelListViewModel(apiKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}