package com.lukic.movieapp

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
import com.lukic.movieapp.adapters.CastAdapter
import com.lukic.movieapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val navArgs by navArgs<DetailsFragmentArgs>()
        val movie = getMovieWithTitle(navArgs.title)
        movie?.let {
            with(binding) {
                detailsScoreText.text = getString(R.string.movie_rating, it.rating.toString())
                ObjectAnimator.ofInt(detailsScoreIndicator, "progress", it.rating).also {
                    it.duration = 1000L
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
                detailsCastRv.adapter = CastAdapter().also {
                    it.submitList(movie.cast)
                }

                detailsBack.setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }

        return binding.root
    }

    private fun getMovieWithTitle(title: String): Movie? {
        return Movies.allMovies.firstOrNull { movie -> movie.title == title }
    }
}
