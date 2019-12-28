package com.example.marvelworld.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworld.BuildConfig
import com.example.marvelworld.R
import com.example.marvelworld.data.MarvelDataAdapter
import com.example.marvelworld.data.MarvelDataAdapter.OnMarvelCharacterClickListener
import com.example.marvelworld.model.MarvelCharacter
import com.example.marvelworld.utils.Utils.setupRecyclerView

/**
 * A simple [Fragment] subclass.
 */
class MarvelListFragment : Fragment(), OnMarvelCharacterClickListener {
    private var adapter: MarvelDataAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marvel_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.marvel_list)
        setupRecyclerView(recyclerView)
        adapter = MarvelDataAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = MarvelListViewModelFactory(MARVEL_API_KEY_PUBLIC)
        val viewModel = ViewModelProviders.of(this, factory).get(MarvelListViewModel::class.java)
        viewModel.marvelCharacters.observe(this, Observer<List<MarvelCharacter?>?> { marvelCharacters ->
            if (marvelCharacters != null && marvelCharacters.isNotEmpty()) {
                Log.d(MarvelListActivity.TAG, "Fetched list of " + marvelCharacters.size + " characters")
                adapter!!.submitList(marvelCharacters)
            }
        })
    }

    override fun onItemClick(characterId: Int) {
        val action = MarvelListFragmentDirections.actionMarvelListFragmentToMarvelDetailFragment(characterId)
        NavHostFragment.findNavController(this).navigate(action)
    }

    companion object {
        const val MARVEL_API_KEY_PUBLIC = BuildConfig.MARVEL_API_KEY_PUBLIC
    }
}