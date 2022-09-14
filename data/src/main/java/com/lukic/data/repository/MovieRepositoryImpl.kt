package com.lukic.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.lukic.data.api.MovieService
import com.lukic.data.database.MovieDao
import com.lukic.data.mapper.MovieMapper
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType
import com.lukic.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

const val DAY_TIME_WINDOW = "day"
const val WEEK_TIME_WINDOW = "week"

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieMapper: MovieMapper,
    private val dao: MovieDao
) : MovieRepository {

    private val refreshTrendingMoviesPublisher = MutableStateFlow(DAY_TIME_WINDOW)

    private val refreshForYouMoviesPublisher = MutableStateFlow(ForYouType.TOP_RATED)

    private val refreshDiscoverMoviesPublisher = MutableStateFlow(ShowType.NOW_PLAYING)

    private val movieDetailsIdPublisher = MutableStateFlow<Int?>(null)

    private val searchQueryPublisher = MutableStateFlow<String?>(null)

    @RequiresApi(Build.VERSION_CODES.N)
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun trendingMovies(): Flow<List<Movie>> = combine(
        refreshTrendingMoviesPublisher
            .mapLatest { timeWindow ->
                movieService.fetchTrendingMovies(timeWindow)?.movies
            },
        dao.fetchMovies()
    ) { trendingMovies, favouriteMovies ->
        movieMapper.toMovies(trendingMovies, favouriteMovies)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun forYouMovies(): Flow<List<Movie>> = combine(
        refreshForYouMoviesPublisher
            .mapLatest { forYouTypeApi ->
                movieService.fetchForYouMovies(movieMapper.toForYouApi(forYouTypeApi))?.movies
            },
        dao.fetchMovies()
    ) { forYouMovies, favouriteMovies ->
        movieMapper.toMovies(forYouMovies, favouriteMovies)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun discoverShows(): Flow<List<Movie>> = combine(
        refreshDiscoverMoviesPublisher
            .mapLatest { showTypeApi ->
                movieService.fetchDiscoverShows(movieMapper.toShowTypeApi(showTypeApi))?.movies
            },
        dao.fetchMovies()
    ) { discoverShows, favouriteMovies ->
        movieMapper.toMovies(discoverShows, favouriteMovies)
    }

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
        movieMapper.toMovie(movieDetails, castAndCrew, isFavourite)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun favouriteMovies(): Flow<List<Movie>> = dao.fetchMovies()
        .mapLatest {
            movieMapper.toFavouriteMovies(it)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun searchMovies(): Flow<List<Movie>> = searchQueryPublisher
        .filterNotNull()
        .mapLatest { query ->
            movieMapper.toMovies(movieService.fetchSearchMovies(query)?.movies, emptyList())
        }

    override suspend fun refreshTrendingMovies(timeWindow: String) =
        refreshTrendingMoviesPublisher.emit(timeWindow)

    override suspend fun refreshForYouMovies(type: ForYouType) =
        refreshForYouMoviesPublisher.emit(type)

    override suspend fun refreshMovieDetails(movieId: Int) = movieDetailsIdPublisher.emit(movieId)

    override suspend fun refreshDiscoverMovies(showType: ShowType) =
        refreshDiscoverMoviesPublisher.emit(showType)

    override suspend fun removeFromFavourites(movie: Movie) =
        dao.deleteMovie(movieMapper.toDbMovie(movie))

    override suspend fun insertFavouriteMovie(movie: Movie) =
        dao.insertMovie(movieMapper.toDbMovie(movie))

    override suspend fun refreshSearchMovies(query: String) = searchQueryPublisher.emit(query)
}
