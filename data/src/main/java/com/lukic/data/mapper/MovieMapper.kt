package com.lukic.data.mapper

import com.lukic.data.api.model.*
import com.lukic.domain.model.*

interface MovieMapper {

    fun toMovies(apiMovies: List<ApiMovie>?): List<Movie>

    fun toMovie(apiMovieDetails: ApiMovieDetails?, castAndCrew: ApiCastAndCrew?): Movie

    fun toShowTypeApi(type: ShowType): ShowTypeApi

    fun toForYouApi(type: ForYouType): ForYouApi

    fun fromApiCastToCast(apiCastAndCrew: ApiCastAndCrew?): List<Cast>

    fun fromApiCrewToCrew(apiCastAndCrew: ApiCastAndCrew?): List<Crew>

    fun fromMinutesToHHmm(minutes: Int): String
}
