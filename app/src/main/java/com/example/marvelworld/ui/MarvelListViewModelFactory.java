package com.example.marvelworld.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MarvelListViewModelFactory implements ViewModelProvider.Factory {

    private String apiKey;

    public MarvelListViewModelFactory(String apiKey) {
        this.apiKey = apiKey;
    }

    @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MarvelListViewModel.class)) {
            return (T) new MarvelListViewModel(apiKey);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
