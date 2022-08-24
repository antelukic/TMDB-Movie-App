package com.lukic.movieapp.domain.repository

import com.lukic.movieapp.domain.model.Movie

interface MovieRepository {

    fun getAllMovies(): List<Movie>

    fun getMovieByID(id: String): Movie?
}
