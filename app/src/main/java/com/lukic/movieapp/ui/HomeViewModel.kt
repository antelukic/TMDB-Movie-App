package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType
import com.lukic.domain.usecase.AddFavouriteMovie
import com.lukic.domain.usecase.QueryDiscoverShows
import com.lukic.domain.usecase.QueryForYouMovies
import com.lukic.domain.usecase.QueryTrendingMovies
import com.lukic.domain.usecase.RefreshDiscoverMovies
import com.lukic.domain.usecase.RefreshForYouMovies
import com.lukic.domain.usecase.RefreshTrendingMovies
import com.lukic.domain.usecase.RemoveFavouriteMovie
import com.lukic.movieapp.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    queryTrendingMovies: QueryTrendingMovies,
    queryDiscoverShows: QueryDiscoverShows,
    queryForYouMovies: QueryForYouMovies,
    private val removeFavouriteMovie: RemoveFavouriteMovie,
    private val addFavouriteMovie: AddFavouriteMovie,
    private val refreshTrendingMovies: RefreshTrendingMovies,
    private val refreshDiscoverMovies: RefreshDiscoverMovies,
    private val refreshForYouMovies: RefreshForYouMovies
) : ViewModel() {

    private val _trendingUIState = MutableStateFlow<List<HomeMovieUIState>>(emptyList())
    val trendingUIState: StateFlow<List<HomeMovieUIState>> get() = _trendingUIState

    private val _discoverUIState = MutableStateFlow<List<HomeMovieUIState>>(emptyList())
    val discoverUIState: StateFlow<List<HomeMovieUIState>> get() = _discoverUIState

    private val _forYouUIState = MutableStateFlow<List<HomeMovieUIState>>(emptyList())
    val forYouUIState: StateFlow<List<HomeMovieUIState>> get() = _forYouUIState

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        queryTrendingMovies()
            .onEach { movies -> _trendingUIState.update { fromMoviesToHomeMovieUIStates(movies) } }
            .launchIn(scope)

        queryForYouMovies()
            .onEach { movies -> _forYouUIState.update { fromMoviesToHomeMovieUIStates(movies) } }
            .launchIn(scope)

        queryDiscoverShows()
            .onEach { movies -> _discoverUIState.update { fromMoviesToHomeMovieUIStates(movies) } }
            .launchIn(scope)
    }

    fun refreshTrendingMovies(timeWindow: String) {
        scope.launch {
            refreshTrendingMovies(time = timeWindow)
        }
    }

    fun refreshForYouMovies(type: ForYouType) {
        scope.launch {
            refreshForYouMovies(forYouType = type)
        }
    }

    fun refreshDiscoverMovies(type: ShowType) {
        scope.launch {
            refreshDiscoverMovies(showType = type)
        }
    }

    fun refreshFavouriteMovies(movieState: HomeMovieUIState) {
        scope.launch {
            val movie = mapStateToMovie(movieState)
            if (movie.isFavourite) {
                removeFavouriteMovie(movie)
            } else {
                addFavouriteMovie(movie)
            }
        }
    }

    private fun mapStateToMovie(movieState: HomeMovieUIState) =
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

    private fun fromMoviesToHomeMovieUIStates(movies: List<Movie>): List<HomeMovieUIState> =
        movies.map { movie ->
            with(movie) {
                HomeMovieUIState(
                    movieID = id,
                    posterPath = BuildConfig.DOMAIN_BASE_IMAGE + posterPath,
                    isFavourite = isFavourite
                )
            }
        }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
