package com.example.marvelworld.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.marvelworld.R;
import com.example.marvelworld.model.MarvelCharacter;

import static com.example.marvelworld.ui.MarvelListActivity.TAG;
import static com.example.marvelworld.ui.MarvelListFragment.MARVEL_API_KEY_PUBLIC;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarvelDetailFragment extends Fragment {

    private ImageView characterImage;
    private TextView name;

    private int marvelCharacterId;

    public MarvelDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marvel_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        characterImage = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);

        if (getArguments() != null) {
            marvelCharacterId = MarvelDetailFragmentArgs.fromBundle(getArguments()).getMarvelCharacterId();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MarvelDetailViewModelFactory factory = new MarvelDetailViewModelFactory(MARVEL_API_KEY_PUBLIC, marvelCharacterId);
        MarvelDetailViewModel viewModel = ViewModelProviders.of(this, factory).get(MarvelDetailViewModel.class);

        viewModel.getMarvelCharacter().observe(this, new Observer<MarvelCharacter>() {
            @Override
            public void onChanged(MarvelCharacter character) {
                if (character != null) {
                    String imageUrl = null;
                    if (character.getThumbnail() != null) {
                        imageUrl = character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension();
                    }
                    Log.d(TAG, "Image url : " + imageUrl);
                    Glide.with(characterImage.getContext())
                            .load(imageUrl)
                            .into(characterImage);
                    name.setText(character.getName());
                }
            }
        });
    }
}
