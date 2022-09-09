package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.domain.model.Cast
import com.lukic.domain.model.Movie
import com.lukic.domain.usecase.AddFavouriteMovie
import com.lukic.domain.usecase.QueryMovieDetails
import com.lukic.domain.usecase.RefreshMovieDetails
import com.lukic.domain.usecase.RemoveFavouriteMovie
import com.lukic.movieapp.BuildConfig
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel(
    private val queryMovieDetails: QueryMovieDetails,
    private val refreshMovieDetails: RefreshMovieDetails,
    private val removeFavouriteMovie: RemoveFavouriteMovie,
    private val addFavouriteMovie: AddFavouriteMovie,
    private val movieID: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailsUIState.DEFAULT)
    val uiState: StateFlow<MovieDetailsUIState> get() = _uiState

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        scope.launch {
            refreshMovieDetails(movieID)

            queryMovieDetails()
                .collect { movie ->
                    _uiState.update {
                        with(movie) {
                            MovieDetailsUIState(
                                title = title,
                                overview = overview,
                                genres = genres,
                                rating = rating,
                                credits = crew,
                                releaseDate = releaseDate,
                                duration = duration,
                                cast = appendBaseImageToPosterPathInCast(cast),
                                posterPath = BuildConfig.DOMAIN_BASE_IMAGE + posterPath,
                                isFavourite = isFavourite
                            )
                        }
                    }
                }
        }
    }

    fun refreshFavouriteMovies() {
        scope.launch {
            val movie = mapStateToMovie(uiState.value)
            if (movie.isFavourite) removeFavouriteMovie(movie)
            else addFavouriteMovie(movie = movie)
        }
    }

    private fun mapStateToMovie(movieState: MovieDetailsUIState) =
        with(movieState) {
            Movie(
                id = movieID,
                title = "",
                overview = "",
                rating = 0,
                genres = emptyList(),
                crew = emptyList(),
                releaseDate = "",
                duration = "",
                cast = emptyList(),
                posterPath = posterPath.removePrefix(BuildConfig.DOMAIN_BASE_IMAGE),
                isFavourite = isFavourite
            )
        }

    private fun appendBaseImageToPosterPathInCast(cast: List<Cast>): List<Cast> = cast.map {
        it.copy(
            posterPath = BuildConfig.DOMAIN_BASE_IMAGE + it.posterPath
        )
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
