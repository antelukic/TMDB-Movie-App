package com.lukic.movieapp.domain.usecase

import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.repository.MovieRepository

class QueryMovieByID(private val movieRepository: MovieRepository) : QueryUseCaseWithParam<String, Movie?> {

    override fun invoke(param: String): Movie? = movieRepository.getMovieByID(param)
}
