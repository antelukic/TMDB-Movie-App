package com.lukic.domain.usecase

import com.lukic.domain.model.Movie
import com.lukic.domain.repository.MovieRepository

class QueryMovieDetails(private val movieRepository: MovieRepository) :
    QueryUseCaseWithParam<Int, Movie> {

    override suspend fun invoke(param: Int): Movie = movieRepository.movieDetails(param)
}
