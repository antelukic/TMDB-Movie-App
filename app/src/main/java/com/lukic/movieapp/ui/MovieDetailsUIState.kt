package com.lukic.movieapp.ui

import com.lukic.movieapp.domain.model.Cast
import com.lukic.movieapp.domain.model.Crew

data class MovieDetailsUIState(
    val title: String,
    val overview: String,
    val genres: List<String>,
    val rating: Int,
    val credits: List<Crew>,
    val releaseDate: String,
    val duration: String,
    val cast: List<Cast>,
    val posterPath: String
)
