package com.lukic.data.api

import com.lukic.data.api.model.*
import io.ktor.client.call.*
import io.ktor.client.request.*

private const val TRENDING_ROUTE = "trending/movie"
private const val BASE_MOVIE_ROUTE = "movie"

class MovieServiceImpl(private val ktor: KtorClient) : MovieService {

    override suspend fun fetchTrendingMovies(timeWindow: String): ApiMovies? = runCatching {
        ktor.client.get {
            url("$TRENDING_ROUTE/$timeWindow")
        }.body() as ApiMovies
    }
        .onFailure {
            it.printStackTrace()
        }
        .getOrNull()

    override suspend fun fetchDiscoverShows(showType: ShowTypeApi): ApiMovies? = runCatching {
        ktor.client.get {
            url("$BASE_MOVIE_ROUTE/${showType.key}")
        }.body() as ApiMovies
    }
        .onFailure {
            it.printStackTrace()
        }
        .getOrNull()

    override suspend fun fetchForYouMovies(type: ForYouApi): ApiMovies? = runCatching {
        ktor.client.get {
            url("$BASE_MOVIE_ROUTE/${type.key}")
        }.body() as ApiMovies
    }
        .onFailure {
            it.printStackTrace()
        }
        .getOrNull()

    override suspend fun fetchMovieDetails(movieId: Int): ApiMovieDetails? = runCatching {
        ktor.client.get {
            url("$BASE_MOVIE_ROUTE/$movieId")
        }.body() as ApiMovieDetails
    }
        .onFailure {
            it.printStackTrace()
        }
        .getOrNull()

    override suspend fun fetchCastAndCrew(movieId: Int): ApiCastAndCrew? = runCatching {
        ktor.client.get {
            url("$BASE_MOVIE_ROUTE/$movieId/credits")
        }.body() as ApiCastAndCrew
    }
        .onFailure {
            it.printStackTrace()
        }
        .getOrNull()
}
