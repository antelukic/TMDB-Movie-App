package com.lukic.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.lukic.data.repository.DAY_TIME_WINDOW
import com.lukic.data.repository.WEEK_TIME_WINDOW
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.ShowType
import com.lukic.movieapp.databinding.FragmentHomeBinding
import com.lukic.movieapp.ui.adapters.MovieAdapter
import com.lukic.movieapp.utils.TabSelectedListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val DETAILS_IMAGE_ID = "details_image"

class HomeFragment : Fragment() {

    private lateinit var forYouMovieAdapter: MovieAdapter
    private lateinit var discoverMovieAdapter: MovieAdapter
    private lateinit var trendingMovieAdapter: MovieAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initAdapters()
        setTabSelectedListeners()
        collectStates()

        with(binding) {
            homeSearchEditText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus)
                    homeCancelText.visibility = View.VISIBLE
            }

            homeTrendingMovies.adapter = trendingMovieAdapter
            homeForYouMovies.adapter = forYouMovieAdapter
            homeDiscoverMovies.adapter = discoverMovieAdapter

            homeCancelText.setOnClickListener {
                homeSearchEditText.text?.clear()
                homeSearchEditText.clearFocus()
                it.visibility = View.GONE
            }
        }
        return binding.root
    }

    private fun initAdapters() {
        forYouMovieAdapter = MovieAdapter(
            onClick = { id, view ->
                onItemClick(id, view)
            }
        )
        discoverMovieAdapter = MovieAdapter(
            onClick = { id, view ->
                onItemClick(id, view)
            }
        )

        trendingMovieAdapter = MovieAdapter(
            onClick = { id, view ->
                onItemClick(id, view)
            }
        )
    }

    private fun collectStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.trendingUIState.collect { movies ->
                        trendingMovieAdapter.submitList(movies)
                    }
                }
                launch {
                    homeViewModel.forYouUIState.collect { movies ->
                        forYouMovieAdapter.submitList(movies)
                    }
                }
                launch {
                    homeViewModel.discoverUIState.collect { movies ->
                        discoverMovieAdapter.submitList(movies)
                    }
                }
            }
        }
    }

    private fun setTabSelectedListeners() {
        with(binding) {
            homeForyouTabs.addOnTabSelectedListener(object : TabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    super.onTabSelected(tab)
                    when (tab?.position) {
                        0 -> homeViewModel.refreshForYouMovies(ForYouType.TOP_RATED)
                        1 -> homeViewModel.refreshForYouMovies(ForYouType.POPULAR)
                    }
                }
            })
            homeDiscoverTabs.addOnTabSelectedListener(object : TabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    super.onTabSelected(tab)
                    when (tab?.position) {
                        0 -> homeViewModel.refreshDiscoverMovies(ShowType.NOW_PLAYING)
                        1 -> homeViewModel.refreshDiscoverMovies(ShowType.UPCOMING)
                    }
                }
            })
            homeTrendingTabs.addOnTabSelectedListener(object : TabSelectedListener {

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    super.onTabSelected(tab)
                    when (tab?.position) {
                        0 -> homeViewModel.refreshTrendingMovies(DAY_TIME_WINDOW)
                        1 -> homeViewModel.refreshTrendingMovies(WEEK_TIME_WINDOW)
                    }
                }
            })
        }
    }

    private fun onItemClick(id: Int, view: View) {
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
