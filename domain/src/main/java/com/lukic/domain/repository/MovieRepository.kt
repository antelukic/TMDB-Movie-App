package com.lukic.domain.repository

import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType

interface MovieRepository {

    suspend fun trendingMovies(timeWindow: String): List<Movie>

    suspend fun discoverShows(showType: ShowType): List<Movie>

    suspend fun forYouMovies(type: ForYouType): List<Movie>

    suspend fun movieDetails(movieId: Int): Movie
}
