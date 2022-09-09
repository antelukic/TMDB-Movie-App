package com.lukic.movieapp.ui

import com.lukic.domain.model.Cast
import com.lukic.domain.model.Crew

data class MovieDetailsUIState(
    val title: String,
    val overview: String,
    val genres: List<String>,
    val rating: Int,
    val credits: List<Crew>,
    val releaseDate: String,
    val duration: String,
    val cast: List<Cast>,
    val posterPath: String,
    val isFavourite: Boolean
) {
    companion object {
        val DEFAULT = MovieDetailsUIState(
            title = "",
            overview = "",
            genres = emptyList(),
            rating = 0,
            credits = emptyList(),
            releaseDate = "",
            duration = "",
            cast = emptyList(),
            posterPath = "",
            isFavourite = false
        )
    }
}
