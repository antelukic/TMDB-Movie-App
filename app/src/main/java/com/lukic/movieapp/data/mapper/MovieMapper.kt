package com.lukic.movieapp.data.mapper

import com.lukic.movieapp.data.api.model.ApiMovie
import com.lukic.movieapp.domain.model.Movie

interface MovieMapper {

    fun toMovies(apiMovies: List<ApiMovie>): List<Movie>
}
