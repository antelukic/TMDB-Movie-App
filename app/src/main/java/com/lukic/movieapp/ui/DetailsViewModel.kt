package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.movieapp.domain.usecase.QueryMovieByID

class DetailsViewModel(private val queryMovieByIDUseCase: QueryMovieByID) : ViewModel() {

    var uiState: MovieDetailsUIState? = null

    fun getMovieByID(id: String) {
        queryMovieByIDUseCase(id)?.let { movie ->
            with(movie) {
                uiState = MovieDetailsUIState(
                    title = title,
                    overview = overview,
                    genres = genres,
                    rating = rating,
                    credits = credits,
                    releaseDate = releaseDate,
                    duration = duration,
                    cast = cast,
                    movieThumbnail = movieThumbnail
                )
            }
        }
    }
}
