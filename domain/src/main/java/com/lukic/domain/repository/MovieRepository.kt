package com.lukic.domain.repository

import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun trendingMovies(): Flow<List<Movie>>

    fun forYouMovies(): Flow<List<Movie>>

    fun discoverShows(): Flow<List<Movie>>

    fun movieDetails(): Flow<Movie>

    fun favouriteMovies(): Flow<List<Movie>>

    fun searchMovies(): Flow<List<Movie>>

    suspend fun refreshTrendingMovies(timeWindow: String)

    suspend fun refreshForYouMovies(type: ForYouType)

    suspend fun refreshDiscoverMovies(showType: ShowType)

    suspend fun removeFromFavourites(movie: Movie)

    suspend fun insertFavouriteMovie(movie: Movie)

    suspend fun refreshMovieDetails(movieId: Int)

    suspend fun refreshSearchMovies(query: String)
}
