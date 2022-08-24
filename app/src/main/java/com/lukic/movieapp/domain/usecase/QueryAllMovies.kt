package com.lukic.movieapp.domain.usecase

import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.repository.MovieRepository

class QueryAllMovies(private val repository: MovieRepository) : QueryUseCase<List<Movie>> {

    override fun invoke(): List<Movie> = repository.getAllMovies()
}
