package com.lukic.domain.usecase

import com.lukic.domain.model.ShowType
import com.lukic.domain.repository.MovieRepository

class RefreshDiscoverMovies(private val repository: MovieRepository) :
    CommandUseCaseWithParam<ShowType> {

    override suspend fun invoke(param: ShowType) = repository.refreshDiscoverMovies(param)
}
