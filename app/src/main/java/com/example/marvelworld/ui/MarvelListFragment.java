package com.example.marvelworld.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marvelworld.BuildConfig;
import com.example.marvelworld.R;
import com.example.marvelworld.data.MarvelDataAdapter;
import com.example.marvelworld.model.MarvelCharacter;
import com.example.marvelworld.utils.Utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.marvelworld.ui.MarvelListActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarvelListFragment extends Fragment implements MarvelDataAdapter.OnMarvelCharacterClickListener {

    public static final String MARVEL_API_KEY_PUBLIC = BuildConfig.MARVEL_API_KEY_PUBLIC;

    private MarvelListViewModel viewModel;
    private RecyclerView recyclerView;
    private MarvelDataAdapter adapter;

    public MarvelListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marvel_list, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.marvel_list);
        Utils.setupRecyclerView(recyclerView);

        adapter = new MarvelDataAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MarvelListViewModelFactory factory = new MarvelListViewModelFactory(MARVEL_API_KEY_PUBLIC);
        viewModel = ViewModelProviders.of(this, factory).get(MarvelListViewModel.class);

        viewModel.getMarvelCharacters().observe(this, new Observer<List<MarvelCharacter>>() {
            @Override public void onChanged(List<MarvelCharacter> marvelCharacters) {
                if (marvelCharacters != null && marvelCharacters.size() > 0) {
                    Log.d(TAG, "Fetched list of " + marvelCharacters.size() + " characters");
                    adapter.submitList(marvelCharacters);
                }
            }
        });
    }

    @Override public void onItemClick(int characterId) {
        MarvelListFragmentDirections.ActionMarvelListFragmentToMarvelDetailFragment action = MarvelListFragmentDirections.actionMarvelListFragmentToMarvelDetailFragment(characterId);
        NavHostFragment.findNavController(this).navigate(action);
    }
}
