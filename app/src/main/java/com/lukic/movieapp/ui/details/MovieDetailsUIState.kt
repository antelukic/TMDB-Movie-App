package com.lukic.movieapp.ui.details

import com.lukic.domain.model.Cast
import com.lukic.domain.model.Crew

data class MovieDetailsUIState(
    val title: String,
    val overview: String,
    val genres: List<String>,
    val rating: Int,
    val progress: Float,
    val crew: List<Crew>,
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
            progress = 0f,
            crew = emptyList(),
            releaseDate = "",
            duration = "",
            cast = emptyList(),
            posterPath = "",
            isFavourite = false
        )
    }
}
