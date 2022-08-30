package com.lukic.domain.usecase

import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.repository.MovieRepository

class QueryForYouMovies(private val repository: MovieRepository) :
    QueryUseCaseWithParam<ForYouType, List<Movie>> {

    override suspend fun invoke(param: ForYouType): List<Movie> = repository.forYouMovies(param)
}
