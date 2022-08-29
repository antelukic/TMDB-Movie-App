package com.lukic.movieapp.domain.usecase

import com.lukic.movieapp.domain.model.ForYouType
import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.repository.MovieRepository

class QueryForYouMovies(private val repository: MovieRepository) :
    QueryUseCaseWithParam<ForYouType, List<Movie>> {

    override suspend fun invoke(param: ForYouType): List<Movie> = repository.forYouMovies(param)
}
