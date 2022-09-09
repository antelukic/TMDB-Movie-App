package com.lukic.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.lukic.movieapp.databinding.FragmentFavouritesBinding
import com.lukic.movieapp.ui.adapters.FavouritesAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavouritesAdapter
    private val viewModel: FavouritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)

        adapter = FavouritesAdapter(
            onImageClick = { movieId ->
                findNavController().navigate(
                    FavouritesFragmentDirections.actionFavouritesFragmentToDetailsFragment(
                        movieId
                    )
                )
            },
            onFavouritesSelectorClicked = { movieId ->
                viewModel.removeFavouriteMovie(movieId)
            }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { favouriteMovies ->
                    adapter.submitList(favouriteMovies)
                }
            }
        }

        binding.favouritesMovies.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
