package com.lukic.movieapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lukic.movieapp.MOVIE_ID_KEY
import com.lukic.movieapp.ui.theme.MovieTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MovieTheme {
                SearchScreen(
                    searchViewModel = viewModel,
                    onItemClick = { movieId ->
                        findNavController().previousBackStackEntry?.savedStateHandle?.set(MOVIE_ID_KEY, movieId)
                    }
                )
            }
        }
    }
}
