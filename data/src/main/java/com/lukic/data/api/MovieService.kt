package com.lukic.data.api

import com.lukic.data.api.model.*


interface MovieService {

    suspend fun fetchTrendingMovies(timeWindow: String): ApiMovies?

    suspend fun fetchDiscoverShows(showType: ShowTypeApi): ApiMovies?

    suspend fun fetchForYouMovies(type: ForYouApi): ApiMovies?

    suspend fun fetchMovieDetails(movieId: Int): ApiMovieDetails?

    suspend fun fetchCastAndCrew(movieId: Int): ApiCastAndCrew?
}
