package com.lukic.domain.usecase

import com.lukic.domain.repository.MovieRepository

class RefreshTrendingMovies(private val repository: MovieRepository) :
    CommandUseCaseWithParam<String> {

    override suspend fun invoke(param: String) = repository.refreshTrendingMovies(param)
}
