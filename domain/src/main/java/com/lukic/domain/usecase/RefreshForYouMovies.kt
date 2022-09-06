package com.lukic.domain.usecase

import com.lukic.domain.model.ForYouType
import com.lukic.domain.repository.MovieRepository

class RefreshForYouMovies(private val repository: MovieRepository) :
    CommandUseCaseWithParam<ForYouType> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun invoke(forYouType: ForYouType) = repository.refreshForYouMovies(forYouType)
}
