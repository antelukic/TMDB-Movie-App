package com.lukic.movieapp.domain.model

import java.util.UUID

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: List<Int>,
    val rating: Int,
    val credits: List<Credits>,
    val releaseDate: String,
    val duration: String,
    val cast: List<Cast>,
    val posterPath: String
)

data class Credits(
    val name: String,
    val role: String
)

data class Cast(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val roleName: String,
    val castThumbnail: String
)
