package com.lukic.movieapp.domain.usecase

import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.model.ShowType
import com.lukic.movieapp.domain.repository.MovieRepository

class QueryDiscoverShows(private val repository: MovieRepository) :
    QueryUseCaseWithParam<ShowType, List<Movie>> {

    override suspend fun invoke(param: ShowType): List<Movie> = repository.discoverShows(param)
}
