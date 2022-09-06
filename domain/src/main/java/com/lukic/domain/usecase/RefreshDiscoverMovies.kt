package com.lukic.domain.usecase

import com.lukic.domain.model.ShowType
import com.lukic.domain.repository.MovieRepository

class RefreshDiscoverMovies(private val repository: MovieRepository) :
    CommandUseCaseWithParam<ShowType> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun invoke(showType: ShowType) = repository.refreshDiscoverMovies(showType)
}
