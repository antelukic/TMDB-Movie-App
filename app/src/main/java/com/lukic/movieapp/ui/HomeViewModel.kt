package com.lukic.movieapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lukic.movieapp.domain.model.ForYouType
import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.model.ShowType
import com.lukic.movieapp.domain.usecase.QueryDiscoverShows
import com.lukic.movieapp.domain.usecase.QueryForYouMovies
import com.lukic.movieapp.domain.usecase.QueryTrendingMovies
import kotlinx.coroutines.*

class HomeViewModel(
    private val queryTrendingMovies: QueryTrendingMovies,
    private val queryDiscoverShows: QueryDiscoverShows,
    private val queryForYouMovies: QueryForYouMovies
) : ViewModel() {

    var forYouUIState: List<HomeMovieUIState> = listOf()
    var discoverUIState: List<HomeMovieUIState> = listOf()
    var trendingUIState: List<HomeMovieUIState> = listOf()

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        getTrendingMovies(DEFAULT_TIME_WINDOW)
        getDiscoverShows(DEFAULT_SHOW_TYPE)
        getForYouMovies(DEFAULT_FOR_YOU_TYPE)
    }

    private fun getTrendingMovies(timeWindow: String) {
        scope.launch {
            trendingUIState = fromMoviesToHomeMovieUIStates(queryTrendingMovies(timeWindow))
            Log.d(TAG, "getMovies: threadName: ${Thread.currentThread().name} trendingUIState: $trendingUIState")
        }
    }

    private fun getDiscoverShows(showType: ShowType) {
        scope.launch {
            discoverUIState = fromMoviesToHomeMovieUIStates(queryDiscoverShows(showType))
            Log.d(TAG, "getDiscoverShows: threadName: ${Thread.currentThread().name} discoverUIState $discoverUIState")
        }
    }

    private fun getForYouMovies(type: ForYouType) {
        scope.launch {
            forYouUIState = fromMoviesToHomeMovieUIStates(queryForYouMovies(type))
            Log.d(TAG, "getForYou: threadName: ${Thread.currentThread().name} forYouMovies $forYouUIState")
        }
    }

    private fun fromMoviesToHomeMovieUIStates(movies: List<Movie>): List<HomeMovieUIState> =
        movies.map { movie ->
            with(movie) {
                HomeMovieUIState(
                    movieID = id,
                    posterPath = posterPath
                )
            }
        }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

    companion object {
        private const val DEFAULT_TIME_WINDOW = "day"
        private val DEFAULT_SHOW_TYPE = ShowType.MOVIE
        private const val TAG = "HomeViewModel"
        private val DEFAULT_FOR_YOU_TYPE = ForYouType.POPULAR
    }
}
