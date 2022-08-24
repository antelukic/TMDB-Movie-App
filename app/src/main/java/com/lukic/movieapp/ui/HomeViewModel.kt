package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.usecase.QueryUseCase

class HomeViewModel(
    private val getAllMovies: QueryUseCase<List<Movie>>
) : ViewModel() {

    var movieUIState: List<HomeMovieUIState> = listOf()

    init {
        getMovies()
    }

    private fun getMovies() {
        movieUIState = getAllMovies().map { movie ->
            HomeMovieUIState(
                movieID = movie.id,
                movieThumbnail = movie.movieThumbnail
            )
        }
    }
}
