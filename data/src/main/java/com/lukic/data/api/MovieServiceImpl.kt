package com.lukic.data.api

import com.lukic.data.api.model.ApiCastAndCrew
import com.lukic.data.api.model.ApiMovieDetails
import com.lukic.data.api.model.ApiMovies
import com.lukic.data.api.model.ForYouApi
import com.lukic.data.api.model.ShowTypeApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

private const val TRENDING_ROUTE = "trending/movie"
private const val BASE_MOVIE_ROUTE = "movie"
private const val SEARCH_MOVIE_ROUTE = "search/movie"
private const val QUERY_KEY_PARAMETER = "query"

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

    override suspend fun fetchSearchMovies(query: String): ApiMovies? = runCatching {
        ktor.client.get {
            url(SEARCH_MOVIE_ROUTE)
            parameter(QUERY_KEY_PARAMETER, query)
        }.body() as ApiMovies
    }
        .onFailure {
            it.printStackTrace()
        }
        .getOrNull()
}
