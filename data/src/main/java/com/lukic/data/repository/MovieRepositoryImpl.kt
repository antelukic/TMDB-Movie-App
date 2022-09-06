package com.lukic.data.repository

import com.lukic.data.api.MovieService
import com.lukic.data.mapper.MovieMapper
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType
import com.lukic.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest

const val DAY_TIME_WINDOW = "day"
const val WEEK_TIME_WINDOW = "week"

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieMapper: MovieMapper
) : MovieRepository {

    private val refreshTrendingMoviesPublisher = MutableStateFlow(DAY_TIME_WINDOW)

    private val refreshForYouMoviesPublisher = MutableStateFlow(ForYouType.TOP_RATED)

    private val refreshDiscoverMoviesPublisher = MutableStateFlow(ShowType.NOW_PLAYING)

    private val movieDetailsIdPublisher = MutableStateFlow<Int?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun trendingMovies(): Flow<List<Movie>> = refreshTrendingMoviesPublisher
        .mapLatest { timeWindow ->
            movieMapper.toMovies(movieService.fetchTrendingMovies(timeWindow)?.movies)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun forYouMovies(): Flow<List<Movie>> = refreshForYouMoviesPublisher
        .mapLatest { forYouType ->
            movieMapper.toMovies(movieService.fetchForYouMovies(movieMapper.toForYouApi(forYouType))?.movies)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun discoverShows(): Flow<List<Movie>> = refreshDiscoverMoviesPublisher
        .mapLatest { showType ->
            movieMapper.toMovies(movieService.fetchDiscoverShows(movieMapper.toShowTypeApi(showType))?.movies)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun movieDetails(): Flow<Movie> = movieDetailsIdPublisher
        .filterNotNull()
        .mapLatest { movieId ->
            val detailsResponse = movieService.fetchMovieDetails(movieId)
            val castAndCrewResponse = movieService.fetchCastAndCrew(movieId)
            movieMapper.toMovie(detailsResponse, castAndCrewResponse)
        }

    override suspend fun refreshTrendingMovies(timeWindow: String) =
        refreshTrendingMoviesPublisher.emit(timeWindow)

    override suspend fun refreshForYouMovies(type: ForYouType) =
        refreshForYouMoviesPublisher.emit(type)

    override suspend fun refreshDiscoverMovies(showType: ShowType) =
        refreshDiscoverMoviesPublisher.emit(showType)

    override suspend fun refreshMovieDetails(movieId: Int) = movieDetailsIdPublisher.emit(movieId)
}
