package com.lukic.domain.usecase

import com.lukic.domain.repository.MovieRepository

class RefreshMovieDetails(private val repository: MovieRepository) : CommandUseCaseWithParam<Int> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun invoke(movieId: Int) = repository.refreshMovieDetails(movieId)
}
