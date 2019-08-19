package com.example.marvelworld.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.marvelworld.R;
import com.example.marvelworld.model.MarvelCharacter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MarvelDataAdapter extends ListAdapter<MarvelCharacter, MarvelDataAdapter.MarvelDataViewHolder> {

    public MarvelDataAdapter() {
        super(new DiffItemCallback());
    }

    @NonNull @Override public MarvelDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MarvelDataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_marvel_list_item,
                parent, false));
    }

    @Override public void onBindViewHolder(@NonNull MarvelDataViewHolder holder, int position) {
        MarvelCharacter marvelCharacter = getItem(position);
        if (marvelCharacter != null) {
            holder.bind(marvelCharacter);
        }
    }

    public static class DiffItemCallback extends DiffUtil.ItemCallback<MarvelCharacter> {

        @Override public boolean areItemsTheSame(@NonNull MarvelCharacter oldItem, @NonNull MarvelCharacter newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override public boolean areContentsTheSame(@NonNull MarvelCharacter oldItem, @NonNull MarvelCharacter newItem) {
            return oldItem.equals(newItem);
        }
    }

    public class MarvelDataViewHolder extends RecyclerView.ViewHolder {

        private ImageView characterImage;
        private TextView name;

        public MarvelDataViewHolder(@NonNull View itemView) {
            super(itemView);
            characterImage = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }

        public void bind(MarvelCharacter marvelCharacter) {

            String imageUrl = marvelCharacter.getThumbnail().getPath() + "." + marvelCharacter.getThumbnail().getExtension();
            Glide.with(characterImage.getContext())
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(characterImage);

            name.setText(marvelCharacter.getName());
        }
    }
}

