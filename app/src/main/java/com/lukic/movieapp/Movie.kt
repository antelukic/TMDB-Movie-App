package com.lukic.movieapp

import java.util.*

/* Used just for testing purposes*/
data class Movie(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val overview: String,
    val genres: List<String>,
    val rating: Int,
    val credits: List<Credits>,
    val releaseDate: String,
    val duration: String,
    val cast: List<Cast>
)

data class Credits(
    val name: String,
    val role: String
)

data class Cast(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val roleName: String
)
