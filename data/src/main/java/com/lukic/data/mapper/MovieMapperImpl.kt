package com.lukic.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.lukic.data.api.model.*
import com.lukic.data.api.model.ForYouApi.Popular
import com.lukic.data.api.model.ForYouApi.TopRated
import com.lukic.data.api.model.ShowTypeApi.NowPlaying
import com.lukic.data.api.model.ShowTypeApi.Upcoming
import com.lukic.domain.model.*
import com.lukic.domain.model.ForYouType.POPULAR
import com.lukic.domain.model.ForYouType.TOP_RATED
import com.lukic.domain.model.ShowType.NOW_PLAYING
import com.lukic.domain.model.ShowType.UPCOMING
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

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
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

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun fromMinutesToHHmm(minutes: Int): String {
        val hours = TimeUnit.MINUTES.toHours(minutes.toLong())
        val remainMinutes = minutes - TimeUnit.HOURS.toMinutes(hours)
        return String.format("%02d:%02d", hours, remainMinutes)
    }

    override fun toShowTypeApi(type: ShowType): ShowTypeApi =
        when (type) {
            NOW_PLAYING -> NowPlaying
            UPCOMING -> Upcoming
        }

    override fun toForYouApi(type: ForYouType): ForYouApi =
        when (type) {
            POPULAR -> Popular
            TOP_RATED -> TopRated
        }
}
