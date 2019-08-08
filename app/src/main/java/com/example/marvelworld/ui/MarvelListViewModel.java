package com.example.marvelworld.ui;

import com.example.marvelworld.data.MarvelDataRepository;
import com.example.marvelworld.model.MarvelCharacter;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MarvelListViewModel extends ViewModel {

    private final LiveData<List<MarvelCharacter>> marvelCharacters;
    private MarvelDataRepository repository;

    public MarvelListViewModel(String apiKey) {
        this.repository = new MarvelDataRepository(apiKey);
        this.marvelCharacters = repository.getMarvelCharacters();
    }

    public LiveData<List<MarvelCharacter>> getMarvelCharacters() {
        return marvelCharacters;
    }
}
