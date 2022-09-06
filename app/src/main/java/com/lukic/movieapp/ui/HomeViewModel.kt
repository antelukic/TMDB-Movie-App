package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType
import com.lukic.domain.usecase.*
import com.lukic.movieapp.BuildConfig
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val queryTrendingMovies: QueryTrendingMovies,
    private val queryDiscoverShows: QueryDiscoverShows,
    private val queryForYouMovies: QueryForYouMovies,
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
        scope.launch {
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

    private fun fromMoviesToHomeMovieUIStates(movies: List<Movie>): List<HomeMovieUIState> =
        movies.map { movie ->
            with(movie) {
                HomeMovieUIState(
                    movieID = id,
                    posterPath = BuildConfig.DOMAIN_BASE_IMAGE + posterPath
                )
            }
        }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
