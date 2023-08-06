package com.lukic.data.api.model

import com.lukic.data.database.DbMovie
import com.lukic.domain.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
data class ApiMovies(
    @SerialName("results")
    val movies: List<ApiMovie>
)

@Serializable
data class ApiMovie(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("release_date")
    val releaseDate: String? = null
)

const val RATING_FACTOR = 10
const val EMPTY = ""

fun List<ApiMovie>?.toMovies(favouriteMovies: List<DbMovie>?) =
    this?.map { apiMovie ->
        with(apiMovie) {
            Movie(
                id = this.id,
                title = title,
                overview = overview,
                rating = (voteAverage * RATING_FACTOR).roundToInt(),
                genres = emptyList(),
                releaseDate = releaseDate.orEmpty(),
                duration = EMPTY,
                cast = emptyList(),
                posterPath = posterPath.orEmpty(),
                crew = emptyList(),
                isFavourite = favouriteMovies?.firstOrNull { it.id == id } != null
            )
        }
    } ?: emptyList()
