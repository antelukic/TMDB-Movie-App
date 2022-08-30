package com.lukic.movieapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val rating: Int,
    val genres: List<String>,
    val crew: List<Crew>,
    val releaseDate: String,
    val duration: String,
    val cast: List<Cast>,
    val posterPath: String
) {
    companion object {
        val EMPTY_MOVIE = Movie(
            id = 0,
            overview = "",
            title = "",
            rating = 0,
            genres = emptyList(),
            crew = emptyList(),
            releaseDate = "",
            duration = "",
            cast = emptyList(),
            posterPath = ""
        )
    }
}

data class Crew(
    val name: String,
    val role: String
)

data class Cast(
    val id: Int,
    val name: String,
    val roleName: String,
    val posterPath: String
)
