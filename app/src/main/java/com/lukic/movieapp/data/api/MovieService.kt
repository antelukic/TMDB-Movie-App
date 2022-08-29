package com.lukic.movieapp.data.api

import com.lukic.movieapp.data.api.model.ApiMovies
import com.lukic.movieapp.data.api.model.ForYouApi
import com.lukic.movieapp.data.api.model.ShowTypeApi
import com.lukic.movieapp.domain.model.ForYouType
import com.lukic.movieapp.domain.model.ShowType

interface MovieService {

    suspend fun fetchTrendingMovies(timeWindow: String): ApiMovies

    suspend fun fetchDiscoverShows(showType: ShowTypeApi): ApiMovies

    suspend fun fetchForYouMovies(type: ForYouApi): ApiMovies
}
