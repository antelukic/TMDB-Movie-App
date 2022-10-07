package com.lukic.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lukic.movieapp.MOVIE_ID_KEY
import com.lukic.movieapp.ui.theme.MovieTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MovieTheme {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    homeViewModel = homeViewModel,
                    onImageClick = { id ->
                        navigateToDetails(id)
                    }
                )
            }
        }
    }

    private fun navigateToDetails(id: Int) =
        findNavController().currentBackStackEntry?.savedStateHandle?.set(MOVIE_ID_KEY, id)
}
