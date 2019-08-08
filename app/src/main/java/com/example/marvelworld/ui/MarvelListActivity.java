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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
