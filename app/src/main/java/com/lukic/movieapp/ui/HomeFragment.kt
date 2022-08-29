package com.lukic.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.lukic.movieapp.databinding.FragmentHomeBinding
import com.lukic.movieapp.ui.adapters.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val DETAILS_IMAGE_ID = "details_image"

class HomeFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        movieAdapter = MovieAdapter(
            onClick = { id, view ->
                onItemClick(id, view)
            }
        )

        with(binding) {
            homeDiscoverRv.adapter =
                MovieAdapter(onClick = { id, view -> onItemClick(id, view) }).also {
                    it.submitList(homeViewModel.discoverUIState)
                }
            homeForyouRv.adapter =
                MovieAdapter(onClick = { id, view -> onItemClick(id, view) }).also {
                    it.submitList(homeViewModel.forYouUIState)
                }
            homeTrendingRv.adapter =
                MovieAdapter(onClick = { id, view -> onItemClick(id, view) }).also {
                    it.submitList(homeViewModel.trendingUIState)
                }

            homeSearchEditText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus)
                    homeCancelText.visibility = View.VISIBLE
            }

            homeCancelText.setOnClickListener {
                homeSearchEditText.text?.clear()
                homeSearchEditText.clearFocus()
                it.visibility = View.GONE
            }
        }
        return binding.root
    }

    private fun onItemClick(id: String, view: View) {
        val extras = FragmentNavigatorExtras(view to DETAILS_IMAGE_ID)
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id),
            extras
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
