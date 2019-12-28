package com.example.marvelworld.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelworld.R

class MarvelListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        const val TAG = "MarvelWorld"
    }
}