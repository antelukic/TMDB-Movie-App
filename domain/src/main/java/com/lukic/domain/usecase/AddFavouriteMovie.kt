package com.lukic.domain.usecase

import com.lukic.domain.model.Movie
import com.lukic.domain.repository.MovieRepository

class AddFavouriteMovie(private val repository: MovieRepository) : CommandUseCaseWithParam<Movie> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun invoke(movie: Movie) = repository.insertFavouriteMovie(movie)
}
