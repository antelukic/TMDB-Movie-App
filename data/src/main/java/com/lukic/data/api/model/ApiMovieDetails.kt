package com.lukic.data.api.model

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import com.lukic.domain.model.Cast
import com.lukic.domain.model.Crew
import com.lukic.domain.model.Movie
import java.util.concurrent.TimeUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
data class ApiMovieDetails(
    @SerialName("id")
    val id: Int,
    @SerialName("genres")
    val genres: List<ApiGenre>,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("title")
    val title: String,
    @SerialName("vote_average")
    val voteAverage: Double,
)

@RequiresApi(VERSION_CODES.GINGERBREAD)
internal fun ApiMovieDetails?.toMovie(
    castAndCrew: ApiCastAndCrew?,
    isFavourite: Boolean
): Movie = this?.let { movieDetails ->
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
            posterPath = posterPath ?: "",
            isFavourite = isFavourite
        )
    }
} ?: Movie.EMPTY_MOVIE

private fun fromApiCastToCast(apiCastAndCrew: ApiCastAndCrew?): List<Cast> =
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

private fun fromApiCrewToCrew(apiCastAndCrew: ApiCastAndCrew?): List<Crew> =
    apiCastAndCrew?.crew?.map { apiCrew ->
        with(apiCrew) {
            Crew(
                name = name,
                role = job
            )
        }
    } ?: emptyList()

@RequiresApi(VERSION_CODES.GINGERBREAD)
private fun fromMinutesToHHmm(minutes: Int): String {
    val hours = TimeUnit.MINUTES.toHours(minutes.toLong())
    val remainMinutes = minutes - TimeUnit.HOURS.toMinutes(hours)
    return String.format("%2dh:%02dm", hours, remainMinutes)
}
