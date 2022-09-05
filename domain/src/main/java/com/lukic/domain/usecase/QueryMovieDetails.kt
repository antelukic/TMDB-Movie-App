package com.lukic.domain.usecase

import com.lukic.domain.model.Movie
import com.lukic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class QueryMovieDetails(private val movieRepository: MovieRepository) :
    QueryUseCaseWithParam<Int, Movie> {

    override fun invoke(param: Int): Flow<Movie> = movieRepository.movieDetails(param)
}
