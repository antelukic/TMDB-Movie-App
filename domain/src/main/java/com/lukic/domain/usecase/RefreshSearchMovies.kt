package com.lukic.domain.usecase

import com.lukic.domain.repository.MovieRepository

class RefreshSearchMovies(private val repository: MovieRepository) :
    CommandUseCaseWithParam<String> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun invoke(query: String) = repository.refreshSearchMovies(query)
}
