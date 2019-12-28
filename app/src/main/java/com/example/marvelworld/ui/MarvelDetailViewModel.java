package com.example.marvelworld.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.marvelworld.data.MarvelDataRepository;
import com.example.marvelworld.model.MarvelCharacter;

public class MarvelDetailViewModel extends ViewModel {

    private LiveData<MarvelCharacter> marvelCharacter;
    private MarvelDataRepository repository;

    public MarvelDetailViewModel(String apiKey, int id) {
        this.repository = new MarvelDataRepository(apiKey);

    }

    public LiveData<MarvelCharacter> getMarvelCharacter(int id) {
        this.marvelCharacter = repository.getMarvelCharacter(id);
        return marvelCharacter;
    }
}
