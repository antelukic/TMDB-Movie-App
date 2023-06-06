package com.lukic.data.api

import com.lukic.data.api.model.ApiCastAndCrew
import com.lukic.data.api.model.ApiMovieDetails
import com.lukic.data.api.model.ApiMovies
import com.lukic.data.api.model.ForYouApi
import com.lukic.data.api.model.ShowTypeApi

interface MovieService {

    suspend fun fetchTrendingMovies(timeWindow: String): ApiMovies?

    suspend fun fetchDiscoverShows(showType: ShowTypeApi): ApiMovies?

    suspend fun fetchForYouMovies(type: ForYouApi): ApiMovies?

    suspend fun fetchMovieDetails(movieId: Int): ApiMovieDetails?

    suspend fun fetchCastAndCrew(movieId: Int): ApiCastAndCrew?

    suspend fun fetchSearchMovies(query: String): ApiMovies?
}
