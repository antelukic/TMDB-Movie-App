package com.lukic.domain.usecase

import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType
import com.lukic.domain.repository.MovieRepository

class QueryDiscoverShows(private val repository: MovieRepository) :
    QueryUseCaseWithParam<ShowType, List<Movie>> {

    override suspend fun invoke(param: ShowType): List<Movie> = repository.discoverShows(param)
}
