package com.lukic.movieapp.domain.usecase

import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.repository.MovieRepository

class QueryMovieDetails(private val movieRepository: MovieRepository) :
    QueryUseCaseWithParam<Int, Movie> {

    override suspend fun invoke(param: Int): Movie = movieRepository.movieDetails(param)
}
