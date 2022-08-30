package com.lukic.domain.usecase

import com.lukic.domain.model.Movie
import com.lukic.domain.repository.MovieRepository

class QueryTrendingMovies(private val repository: MovieRepository) :
    QueryUseCaseWithParam<String, List<Movie>> {

    override suspend fun invoke(param: String): List<Movie> = repository.trendingMovies(param)
}
