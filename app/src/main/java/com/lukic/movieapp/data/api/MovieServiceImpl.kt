package com.lukic.movieapp.data.api

import com.lukic.movieapp.data.api.model.ApiMovies
import com.lukic.movieapp.data.api.model.ForYouApi
import com.lukic.movieapp.data.api.model.ShowTypeApi
import com.lukic.movieapp.domain.model.ShowType
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

private const val TRENDING_ROUTE = "/3/trending/movie"
private const val DISCOVER_ROUTE = "/3/discover"
private const val FOR_YOU_ROUTE = "/3/movie"

class MovieServiceImpl(private val ktor: KtorClient) : MovieService {

    override suspend fun fetchTrendingMovies(timeWindow: String): ApiMovies = try {
        ktor.client.get {
            url("$TRENDING_ROUTE/$timeWindow")
        }.body()
    } catch (e: Exception) {
        e.printStackTrace()
        ApiMovies(emptyList())
    }

    override suspend fun fetchDiscoverShows(showType: ShowTypeApi): ApiMovies = try {
        ktor.client.get {
            url("$DISCOVER_ROUTE/${showType.key}")
        }.body()
    } catch (e: Exception) {
        e.printStackTrace()
        ApiMovies(emptyList())
    }

    override suspend fun fetchForYouMovies(type: ForYouApi): ApiMovies = try {
        ktor.client.get {
            url("$FOR_YOU_ROUTE/${type.key}")
        }.body()
    } catch (e: Exception) {
        e.printStackTrace()
        ApiMovies(emptyList())
    }
}
