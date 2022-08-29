package com.lukic.movieapp.domain.repository

import com.lukic.movieapp.domain.model.ForYouType
import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.model.ShowType

interface MovieRepository {

    suspend fun trendingMovies(timeWindow: String): List<Movie>

    suspend fun discoverShows(showType: ShowType): List<Movie>

    suspend fun forYouMovies(type: ForYouType): List<Movie>
}
