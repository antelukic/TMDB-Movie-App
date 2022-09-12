package com.lukic.movieapp.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.lukic.movieapp.R
import com.lukic.movieapp.databinding.FragmentDetailsBinding
import com.lukic.movieapp.ui.adapters.CastAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val INDEX_1 = 1
private const val INDEX_2 = 2
private const val INDEX_3 = 3
private const val INDEX_4 = 4
private const val INDEX_5 = 5
private const val INDEX_6 = 6
private const val INDICATOR_ANIMATION_DURATION = 1000L

class DetailsFragment : Fragment() {

    private val detailsViewModel by viewModel<DetailsViewModel> { parametersOf(arguments?.getInt("movieId")) }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    detailsViewModel.uiState.collect {
                        updateUI(it)
                    }
                }
            }
        }

        return binding.root
    }

    private fun updateUI(movieDetails: MovieDetailsUIState) {
        with(binding) {
            detailsMovieImage.load(movieDetails.posterPath)
            detailsTitleText.text = movieDetails.title
            detailsScoreText.text =
                getString(R.string.movie_rating, movieDetails.rating.toString())
            ObjectAnimator.ofInt(detailsScoreIndicator, "progress", movieDetails.rating)
                .also {
                    it.duration = INDICATOR_ANIMATION_DURATION
                    it.interpolator = LinearInterpolator()
                    it.start()
                }
            detailsDateText.text = movieDetails.releaseDate
            detailsGenreText.text = buildString {
                movieDetails.genres.forEachIndexed { index, genre ->
                    if (index == 0) {
                        append(genre)
                    } else {
                        append(", $genre")
                    }
                }
            }
            detailsTotaltimeText.text = movieDetails.duration
            detailsDescriptionText.text = movieDetails.overview
            setMovieCredits(movieDetails)

            with(detailsFavouriteSelector) {
                setImageResource(
                    if (movieDetails.isFavourite) {
                        R.drawable.ic_filled_heart_with_background
                    } else {
                        R.drawable.ic_heart
                    }
                )
                setOnClickListener {
                    detailsViewModel.refreshFavouriteMovies()
                }
            }

            detailsCastMembers.adapter = CastAdapter().also { castAdapter ->
                castAdapter.submitList(movieDetails.cast)
            }
        }
    }

    private fun setMovieCredits(movieDetails: MovieDetailsUIState) {
        with(binding) {
            movieDetails.credits.forEachIndexed { index, credits ->
                when (index) {
                    INDEX_1 -> {
                        detailsCreditsName1.text = credits.name
                        detailsCreditsType1.text = credits.role
                    }

                    INDEX_2 -> {
                        detailsCreditsName2.text = credits.name
                        detailsCreditsType2.text = credits.role
                    }

                    INDEX_3 -> {
                        detailsCreditsName3.text = credits.name
                        detailsCreditsType3.text = credits.role
                    }

                    INDEX_4 -> {
                        detailsCreditsName4.text = credits.name
                        detailsCreditsType4.text = credits.role
                    }

                    INDEX_5 -> {
                        detailsCreditsName5.text = credits.name
                        detailsCreditsType5.text = credits.role
                    }

                    INDEX_6 -> {
                        detailsCreditsName6.text = credits.name
                        detailsCreditsType6.text = credits.role
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
