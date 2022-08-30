package com.lukic.movieapp.data.mapper

import com.lukic.movieapp.data.api.model.ApiCastAndCrew
import com.lukic.movieapp.data.api.model.ApiMovie
import com.lukic.movieapp.data.api.model.ApiMovieDetails
import com.lukic.movieapp.data.api.model.ForYouApi
import com.lukic.movieapp.data.api.model.ShowTypeApi
import com.lukic.movieapp.domain.model.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

private const val RATING_FACTOR = 10

class MovieMapperImpl : MovieMapper {

    override fun toMovies(apiMovies: List<ApiMovie>?): List<Movie> =
        apiMovies?.map { apiMovie ->
            with(apiMovie) {
                Movie(
                    id = this.id,
                    title = title,
                    overview = overview,
                    rating = (voteAverage * RATING_FACTOR).roundToInt(),
                    genres = emptyList(),
                    releaseDate = releaseDate,
                    duration = "",
                    cast = emptyList(),
                    posterPath = posterPath,
                    crew = emptyList()
                )
            }
        } ?: emptyList()

    override fun toMovie(
        apiMovieDetails: ApiMovieDetails?,
        castAndCrew: ApiCastAndCrew?
    ): Movie = apiMovieDetails?.let { movieDetails ->
        with(movieDetails) {
            Movie(
                id = id,
                title = title,
                overview = overview,
                genres = genres.map { it.name },
                rating = (voteAverage * RATING_FACTOR).roundToInt(),
                crew = fromApiCrewToCrew(castAndCrew),
                releaseDate = releaseDate,
                duration = fromMinutesToHHmm(runtime),
                cast = fromApiCastToCast(castAndCrew),
                posterPath = posterPath ?: ""
            )
        }
    } ?: Movie.EMPTY_MOVIE

    override fun fromApiCastToCast(apiCastAndCrew: ApiCastAndCrew?): List<Cast> =
        apiCastAndCrew?.cast?.map { apiCast ->
            with(apiCast) {
                Cast(
                    id = id,
                    name = name,
                    roleName = character,
                    posterPath = posterPath ?: ""
                )
            }
        } ?: emptyList()

    override fun fromApiCrewToCrew(apiCastAndCrew: ApiCastAndCrew?): List<Crew> =
        apiCastAndCrew?.crew?.map { apiCrew ->
            with(apiCrew) {
                Crew(
                    name = name,
                    role = job
                )
            }
        } ?: emptyList()

    override fun fromMinutesToHHmm(minutes: Int): String {
        val hours = TimeUnit.MINUTES.toHours(minutes.toLong())
        val remainMinutes = minutes - TimeUnit.HOURS.toMinutes(hours)
        return String.format("%02d:%02d", hours, remainMinutes)
    }

    override fun toShowTypeApi(type: ShowType): ShowTypeApi =
        when (type) {
            ShowType.MOVIE -> ShowTypeApi.Movie
            ShowType.TV -> ShowTypeApi.Tv
        }

    override fun toForYouApi(type: ForYouType): ForYouApi =
        when (type) {
            ForYouType.POPULAR -> ForYouApi.Popular
            ForYouType.TOP_RATED -> ForYouApi.TopRated
        }
}
