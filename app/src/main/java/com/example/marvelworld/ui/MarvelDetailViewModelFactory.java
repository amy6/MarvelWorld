package com.example.marvelworld.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MarvelDetailViewModelFactory implements ViewModelProvider.Factory {

    private String apiKey;
    private int id;

    public MarvelDetailViewModelFactory(String apiKey, int id) {
        this.apiKey = apiKey;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MarvelDetailViewModel.class)) {
            return (T) new MarvelDetailViewModel(apiKey, id);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
