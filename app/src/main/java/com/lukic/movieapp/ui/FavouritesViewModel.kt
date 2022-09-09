package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.domain.model.Movie
import com.lukic.domain.usecase.QueryFavouriteMovies
import com.lukic.domain.usecase.RemoveFavouriteMovie
import com.lukic.movieapp.BuildConfig
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class FavouritesViewModel(
    queryFavouriteMovies: QueryFavouriteMovies,
    private val removeFavouriteMovie: RemoveFavouriteMovie
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<FavouritesUIState>>(emptyList())
    val uiState: StateFlow<List<FavouritesUIState>> get() = _uiState

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        queryFavouriteMovies()
            .onEach { movies ->
                _uiState.update {
                    movies.map { movie ->
                        FavouritesUIState(
                            movieID = movie.id,
                            posterPath = BuildConfig.DOMAIN_BASE_IMAGE + movie.posterPath,
                        )
                    }
                }
            }.launchIn(scope)
    }

    fun removeFavouriteMovie(movieState: FavouritesUIState) {
        scope.launch {
            val movie = mapStateToMovie(movieState)
            removeFavouriteMovie(movie = movie)
        }
    }

    private fun mapStateToMovie(movieState: FavouritesUIState) =
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
                isFavourite = true
            )
        }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
