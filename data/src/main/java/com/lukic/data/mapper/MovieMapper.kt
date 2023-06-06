package com.lukic.data.mapper

import com.lukic.data.api.model.ApiCastAndCrew
import com.lukic.data.api.model.ApiMovie
import com.lukic.data.api.model.ApiMovieDetails
import com.lukic.data.api.model.ForYouApi
import com.lukic.data.api.model.ShowTypeApi
import com.lukic.data.database.DbMovie
import com.lukic.domain.model.Cast
import com.lukic.domain.model.Crew
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType

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
