package com.lukic.movieapp.domain.usecase

import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.repository.MovieRepository

class QueryTrendingMovies(private val repository: MovieRepository) :
    QueryUseCaseWithParam<String, List<Movie>> {

    override suspend fun invoke(param: String): List<Movie> = repository.trendingMovies(param)
}
