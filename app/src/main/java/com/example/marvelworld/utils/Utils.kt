package com.example.marvelworld.utils

import androidx.recyclerview.widget.RecyclerView

object Utils {
    fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
    }
}