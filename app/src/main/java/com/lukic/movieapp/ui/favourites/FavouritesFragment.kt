package com.lukic.movieapp.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lukic.movieapp.MOVIE_ID_KEY
import com.lukic.movieapp.ui.theme.MovieTheme
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {

    private val viewModel: FavouritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val uiState = viewModel.uiState.collectAsState()
            MovieTheme {
                FavouritesScreen(
                    movies = uiState.value.toImmutableList(),
                    onFavouriteSelectorClick = viewModel::removeFavouriteMovie,
                    onImageClick = this@FavouritesFragment::navigateToDetails
                )
            }
        }
    }

    private fun navigateToDetails(movieId: Int) =
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            MOVIE_ID_KEY,
            movieId
        )
}
