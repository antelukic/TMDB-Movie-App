package com.lukic.movieapp.data.mapper

import com.lukic.movieapp.data.api.model.ApiMovie
import com.lukic.movieapp.domain.model.Movie
import kotlin.math.roundToInt

private const val RATING_FACTOR = 10

class MovieMapperImpl : MovieMapper {

    override fun toMovies(apiMovies: List<ApiMovie>): List<Movie> =
        apiMovies.map { apiMovie ->
            with(apiMovie) {
                Movie(
                    id = this.id,
                    title = title,
                    overview = overview,
                    genres = genreIds,
                    rating = (voteAverage * RATING_FACTOR).roundToInt(),
                    credits = emptyList(),
                    releaseDate = releaseDate,
                    duration = "",
                    cast = emptyList(),
                    posterPath = posterPath
                )
            }
        }
}
