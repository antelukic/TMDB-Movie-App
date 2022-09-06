package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.domain.model.Cast
import com.lukic.domain.usecase.QueryMovieDetails
import com.lukic.domain.usecase.RefreshMovieDetails
import com.lukic.movieapp.BuildConfig
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel(
    private val queryMovieDetails: QueryMovieDetails,
    private val refreshMovieDetails: RefreshMovieDetails,
    movieID: Int
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
                                posterPath = BuildConfig.DOMAIN_BASE_IMAGE + posterPath
                            )
                        }
                    }
                }
        }
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
