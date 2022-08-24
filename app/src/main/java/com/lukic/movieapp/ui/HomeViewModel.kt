package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.movieapp.domain.usecase.QueryAllMovies

class HomeViewModel(private val getAllMovies: QueryAllMovies) : ViewModel() {

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
