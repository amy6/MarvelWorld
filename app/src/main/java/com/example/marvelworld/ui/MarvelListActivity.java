package com.example.marvelworld.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.example.marvelworld.BuildConfig;
import com.example.marvelworld.R;
import com.example.marvelworld.model.MarvelCharacter;

import java.util.List;

public class MarvelListActivity extends AppCompatActivity {

    public static final String TAG = "MarvelWorld";
    public static final String MARVEL_API_KEY_PUBLIC = BuildConfig.MARVEL_API_KEY_PUBLIC;

    private MarvelListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MarvelListViewModelFactory factory = new MarvelListViewModelFactory(MARVEL_API_KEY_PUBLIC);
        viewModel = ViewModelProviders.of(this, factory).get(MarvelListViewModel.class);

        viewModel.getMarvelCharacters().observe(this, new Observer<List<MarvelCharacter>>() {
            @Override public void onChanged(List<MarvelCharacter> marvelCharacters) {
                if (marvelCharacters != null && marvelCharacters.size() > 0) {
                    Log.d(TAG, "Fetched list of " + marvelCharacters.size() + " characters");
                }
            }
        });
    }
}
