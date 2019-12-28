package com.example.marvelworld.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.marvelworld.R

/**
 * A simple [Fragment] subclass.
 */
class MarvelDetailFragment : Fragment() {
    private var characterImage: ImageView? = null
    private var name: TextView? = null
    private var marvelCharacterId = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marvel_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterImage = view.findViewById(R.id.image)
        name = view.findViewById(R.id.name)
        if (arguments != null) {
            marvelCharacterId = MarvelDetailFragmentArgs.fromBundle(arguments!!).marvelCharacterId
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = MarvelDetailViewModelFactory(MarvelListFragment.MARVEL_API_KEY_PUBLIC, marvelCharacterId)
        val viewModel = ViewModelProviders.of(this, factory).get(MarvelDetailViewModel::class.java)
        viewModel.getMarvelCharacter().observe(this, Observer { character ->
            if (character != null) {
                var imageUrl: String? = null
                if (character.thumbnail != null) {
                    imageUrl = character.thumbnail!!.path + "." + character.thumbnail!!.extension
                }
                Log.d(MarvelListActivity.TAG, "Image url : $imageUrl")
                Glide.with(characterImage!!.context)
                        .load(imageUrl)
                        .into(characterImage!!)
                name!!.text = character.name
            }
        })
    }
}