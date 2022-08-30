package com.lukic.movieapp.data.mapper

import com.lukic.movieapp.data.api.model.ApiCastAndCrew
import com.lukic.movieapp.data.api.model.ApiMovie
import com.lukic.movieapp.data.api.model.ApiMovieDetails
import com.lukic.movieapp.data.api.model.ForYouApi
import com.lukic.movieapp.data.api.model.ShowTypeApi
import com.lukic.movieapp.domain.model.*
import java.util.concurrent.TimeUnit

interface MovieMapper {

    fun toMovies(apiMovies: List<ApiMovie>?): List<Movie>

    fun toMovie(apiMovieDetails: ApiMovieDetails?, castAndCrew: ApiCastAndCrew?): Movie

    fun toShowTypeApi(type: ShowType): ShowTypeApi

    fun toForYouApi(type: ForYouType): ForYouApi

    fun fromApiCastToCast(apiCastAndCrew: ApiCastAndCrew?): List<Cast>

    fun fromApiCrewToCrew(apiCastAndCrew: ApiCastAndCrew?): List<Crew>

    fun fromMinutesToHHmm(minutes: Int): String
}
