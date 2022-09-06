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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.lukic.movieapp.R
import com.lukic.movieapp.databinding.FragmentDetailsBinding
import com.lukic.movieapp.ui.adapters.CastAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val INDICATOR_ANIMATION_DURATION = 1000L

class DetailsFragment : Fragment() {

    private val navArgs by navArgs<DetailsFragmentArgs>()
    private val detailsViewModel by viewModel<DetailsViewModel> { parametersOf(navArgs.id) }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.slide_left,
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
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

        binding.detailsBack.setOnClickListener {
            findNavController().navigateUp()
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
                    if (index == 0) append(genre)
                    else append(", $genre")
                }
            }
            detailsTotaltimeText.text = movieDetails.duration
            detailsDescriptionText.text = movieDetails.overview
            setMovieCredits(movieDetails)

            detailsCastMembers.adapter = CastAdapter().also { castAdapter ->
                castAdapter.submitList(movieDetails.cast)
            }
        }
    }

    private fun setMovieCredits(movieDetails: MovieDetailsUIState) {
        with(binding) {
            movieDetails.credits.forEachIndexed { index, credits ->
                when (index) {
                    1 -> {
                        detailsCreditsName1.text = credits.name
                        detailsCreditsType1.text = credits.role
                    }
                    2 -> {
                        detailsCreditsName2.text = credits.name
                        detailsCreditsType2.text = credits.role
                    }
                    3 -> {
                        detailsCreditsName3.text = credits.name
                        detailsCreditsType3.text = credits.role
                    }
                    4 -> {
                        detailsCreditsName4.text = credits.name
                        detailsCreditsType4.text = credits.role
                    }
                    5 -> {
                        detailsCreditsName5.text = credits.name
                        detailsCreditsType5.text = credits.role
                    }
                    6 -> {
                        detailsCreditsName6.text = credits.name
                        detailsCreditsType6.text = credits.role
                    }
                }
            }
        }
    }
}
