package com.example.marvelworld.utils;

import androidx.recyclerview.widget.RecyclerView;

public class Utils {
    public static void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
    }
}
