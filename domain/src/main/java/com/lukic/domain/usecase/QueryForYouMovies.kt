package com.lukic.domain.usecase

import com.lukic.domain.model.Movie
import com.lukic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class QueryForYouMovies(private val repository: MovieRepository) :
    QueryUseCase<List<Movie>> {

    override fun invoke(): Flow<List<Movie>> = repository.forYouMovies()
}
