package com.lukic.data.mapper

import com.lukic.data.api.model.*
import com.lukic.data.database.DbMovie
import com.lukic.domain.model.*

interface MovieMapper {

    fun toMovies(apiMovies: List<ApiMovie>?, favouriteMovies: List<DbMovie>?): List<Movie>

    fun toFavouriteMovies(dbMovies: List<DbMovie>?): List<Movie>

    fun toMovie(
        apiMovieDetails: ApiMovieDetails?,
        castAndCrew: ApiCastAndCrew?,
        isFavourite: Boolean
    ): Movie

    fun toDbMovie(movie: Movie): DbMovie

    fun toShowTypeApi(type: ShowType): ShowTypeApi

    fun toForYouApi(type: ForYouType): ForYouApi

    fun fromApiCastToCast(apiCastAndCrew: ApiCastAndCrew?): List<Cast>

    fun fromApiCrewToCrew(apiCastAndCrew: ApiCastAndCrew?): List<Crew>

    fun fromMinutesToHHmm(minutes: Int): String
}
