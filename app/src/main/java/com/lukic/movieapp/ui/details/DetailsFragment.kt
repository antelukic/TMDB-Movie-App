package com.lukic.movieapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {

    private val detailsViewModel by viewModel<DetailsViewModel> { parametersOf(arguments?.getInt("movieId")) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            DetailsScreen(detailsViewModel = detailsViewModel)
        }
    }
}
