package com.lukic.movieapp.ui.home

data class HomeMovieUIState(
    val movieID: Int,
    val posterPath: String,
    val isFavourite: Boolean
)
