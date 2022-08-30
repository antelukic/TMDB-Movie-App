package com.lukic.movieapp.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.lukic.movieapp.R
import com.lukic.movieapp.databinding.FragmentDetailsBinding
import com.lukic.movieapp.ui.adapters.CastAdapter
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


        detailsViewModel.uiState?.let {
            with(binding) {
                detailsMovieImage.load(it.posterPath)
                detailsScoreText.text = getString(R.string.movie_rating, it.rating.toString())
                ObjectAnimator.ofInt(detailsScoreIndicator, "progress", it.rating).also {
                    it.duration = INDICATOR_ANIMATION_DURATION
                    it.interpolator = LinearInterpolator()
                    it.start()
                }
                detailsDateText.text = it.releaseDate
                detailsGenreText.text = buildString {
                    it.genres.forEachIndexed { index, genre ->
                        if (index == 0) append(genre)
                        else append(", $genre")
                    }
                }
                detailsTotaltimeText.text = it.duration
                detailsDescriptionText.text = it.overview
                setMovieCredits()

                detailsCastRv.adapter = CastAdapter().also { castAdapter ->
                    castAdapter.submitList(it.cast)
                }
            }
        }

        binding.detailsBack.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun setMovieCredits() {
        detailsViewModel.uiState?.let {
            with(binding) {
                it.credits.forEachIndexed { index, credits ->
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
}
