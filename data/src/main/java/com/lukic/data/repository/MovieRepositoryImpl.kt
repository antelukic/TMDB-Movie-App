package com.lukic.data.repository

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import com.lukic.data.api.MovieService
import com.lukic.data.api.model.toForYouApi
import com.lukic.data.api.model.toMovie
import com.lukic.data.api.model.toMovies
import com.lukic.data.api.model.toShowTypeApi
import com.lukic.data.database.MovieDao
import com.lukic.data.database.toDbMovie
import com.lukic.data.database.toFavouriteMovies
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType
import com.lukic.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest

const val DAY_TIME_WINDOW = "day"
const val WEEK_TIME_WINDOW = "week"

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val dao: MovieDao
) : MovieRepository {

    private val refreshTrendingMoviesPublisher = MutableStateFlow(DAY_TIME_WINDOW)

    private val refreshForYouMoviesPublisher = MutableStateFlow(ForYouType.TOP_RATED)

    private val refreshDiscoverMoviesPublisher = MutableStateFlow(ShowType.NOW_PLAYING)

    private val movieDetailsIdPublisher = MutableStateFlow<Int?>(null)

    private val searchQueryPublisher = MutableStateFlow<String?>(null)

    @RequiresApi(VERSION_CODES.N)
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun trendingMovies(): Flow<List<Movie>> = combine(
        refreshTrendingMoviesPublisher
            .mapLatest { timeWindow ->
                movieService.fetchTrendingMovies(timeWindow)?.movies
            },
        dao.fetchMovies()
    ) { trendingMovies, favouriteMovies ->
        trendingMovies.toMovies(favouriteMovies)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun forYouMovies(): Flow<List<Movie>> = combine(
        refreshForYouMoviesPublisher
            .mapLatest { forYouTypeApi ->
                movieService.fetchForYouMovies(forYouTypeApi.toForYouApi())?.movies
            },
        dao.fetchMovies()
    ) { forYouMovies, favouriteMovies ->
        forYouMovies.toMovies(favouriteMovies)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun discoverShows(): Flow<List<Movie>> = combine(
        refreshDiscoverMoviesPublisher
            .mapLatest { showTypeApi ->
                movieService.fetchDiscoverShows(showTypeApi.toShowTypeApi())?.movies
            },
        dao.fetchMovies()
    ) { discoverShows, favouriteMovies ->
        discoverShows.toMovies(favouriteMovies)
    }

    @RequiresApi(VERSION_CODES.GINGERBREAD)
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun movieDetails(): Flow<Movie> = combine(
        movieDetailsIdPublisher
            .filterNotNull()
            .mapLatest { movieId ->
                movieService.fetchMovieDetails(movieId) to movieService.fetchCastAndCrew(movieId)
            },
        dao.fetchMovies()
    ) { (movieDetails, castAndCrew), favouriteMovies ->
        val isFavourite = favouriteMovies.find { dbMovie -> dbMovie.id == movieDetails?.id } != null
        movieDetails.toMovie(castAndCrew, isFavourite)
    }

    @RequiresApi(VERSION_CODES.N)
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun favouriteMovies(): Flow<List<Movie>> = dao.fetchMovies()
        .mapLatest {
            it.toFavouriteMovies()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun searchMovies(): Flow<List<Movie>> = searchQueryPublisher
        .filterNotNull()
        .mapLatest { query ->
            movieService.fetchSearchMovies(query)?.movies.toMovies(emptyList())
        }

    override suspend fun refreshTrendingMovies(timeWindow: String) =
        refreshTrendingMoviesPublisher.emit(timeWindow)

    override suspend fun refreshForYouMovies(type: ForYouType) =
        refreshForYouMoviesPublisher.emit(type)

    override suspend fun refreshMovieDetails(movieId: Int) = movieDetailsIdPublisher.emit(movieId)

    override suspend fun refreshDiscoverMovies(showType: ShowType) =
        refreshDiscoverMoviesPublisher.emit(showType)

    override suspend fun removeFromFavourites(movie: Movie) =
        dao.deleteMovie(movie.toDbMovie())

    override suspend fun insertFavouriteMovie(movie: Movie) =
        dao.insertMovie(movie.toDbMovie())

    override suspend fun refreshSearchMovies(query: String) = searchQueryPublisher.emit(query)
}
