package com.example.marvelworld.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.marvelworld.R
import com.example.marvelworld.data.MarvelDataAdapter.MarvelDataViewHolder
import com.example.marvelworld.model.MarvelCharacter

class MarvelDataAdapter(private val itemClickListener: OnMarvelCharacterClickListener?) : ListAdapter<MarvelCharacter?, MarvelDataViewHolder>(DiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelDataViewHolder {
        return MarvelDataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_marvel_list_item,
                parent, false))
    }

    override fun onBindViewHolder(holder: MarvelDataViewHolder, position: Int) {
        val marvelCharacter = getItem(position)
        if (marvelCharacter != null) {
            holder.bind(marvelCharacter)
        }
    }

    interface OnMarvelCharacterClickListener {
        fun onItemClick(characterId: Int)
    }

    class DiffItemCallback : DiffUtil.ItemCallback<MarvelCharacter?>() {
        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem == newItem
        }
    }

    inner class MarvelDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterImage: ImageView = itemView.findViewById(R.id.image)
        fun bind(marvelCharacter: MarvelCharacter) {
            val imageUrl = marvelCharacter.thumbnail.path + "." + marvelCharacter.thumbnail.extension
            Glide.with(characterImage.context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(characterImage)
            val id = marvelCharacter.id
            itemView.setOnClickListener { itemClickListener?.onItemClick(id) }
        }

    }

}