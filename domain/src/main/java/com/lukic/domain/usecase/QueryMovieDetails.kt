package com.lukic.domain.usecase

import com.lukic.domain.model.Movie
import com.lukic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class QueryMovieDetails(private val movieRepository: MovieRepository) :
    QueryUseCase<Movie> {

    override fun invoke(): Flow<Movie> = movieRepository.movieDetails()
}
