package com.lukic.domain.usecase

import com.lukic.domain.model.ForYouType
import com.lukic.domain.repository.MovieRepository

class RefreshForYouMovies(private val repository: MovieRepository) :
    CommandUseCaseWithParam<ForYouType> {

    override suspend fun invoke(param: ForYouType) = repository.refreshForYouMovies(param)
}
