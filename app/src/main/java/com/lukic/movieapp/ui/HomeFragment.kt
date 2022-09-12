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
import com.google.android.material.tabs.TabLayout
import com.lukic.data.repository.DAY_TIME_WINDOW
import com.lukic.data.repository.WEEK_TIME_WINDOW
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.ShowType
import com.lukic.movieapp.MOVIE_ID_KEY
import com.lukic.movieapp.databinding.FragmentHomeBinding
import com.lukic.movieapp.ui.adapters.MovieAdapter
import com.lukic.movieapp.utils.MovieItemAnimator
import com.lukic.movieapp.utils.TabSelectedListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
            homeTrendingMovies.apply {
                adapter = trendingMovieAdapter
                itemAnimator = MovieItemAnimator()
            }
            homeForYouMovies.apply {
                adapter = forYouMovieAdapter
                itemAnimator = MovieItemAnimator()
            }
            homeDiscoverMovies.apply {
                adapter = discoverMovieAdapter
                itemAnimator = MovieItemAnimator()
            }
        }
        return binding.root
    }

    private fun initAdapters() {
        forYouMovieAdapter = MovieAdapter(
            onCardClick = { id ->
                onItemClick(id)
            },
            onFavouriteClick = { movieState ->
                homeViewModel.refreshFavouriteMovies(movieState)
            }
        )
        discoverMovieAdapter = MovieAdapter(
            onCardClick = { id ->
                onItemClick(id)
            },
            onFavouriteClick = { movieState ->
                homeViewModel.refreshFavouriteMovies(movieState)
            }
        )

        trendingMovieAdapter = MovieAdapter(
            onCardClick = { id ->
                onItemClick(id)
            },
            onFavouriteClick = { movieState ->
                homeViewModel.refreshFavouriteMovies(movieState)
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

    private fun onItemClick(id: Int) =
        findNavController().currentBackStackEntry?.savedStateHandle?.set(MOVIE_ID_KEY, id)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
