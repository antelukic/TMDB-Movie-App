package com.lukic.movieapp.di

import com.lukic.movieapp.data.FakeMovieRepositoryImpl
import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.repository.MovieRepository
import com.lukic.movieapp.domain.usecase.QueryAllMovies
import com.lukic.movieapp.domain.usecase.QueryMovieByID
import com.lukic.movieapp.domain.usecase.QueryUseCase
import com.lukic.movieapp.domain.usecase.QueryUseCaseWithParam

object ObjectGraph {

    private val movieRepository: MovieRepository = FakeMovieRepositoryImpl()

    val queryAllMovies: QueryUseCase<List<Movie>> = QueryAllMovies(movieRepository)
    val queryMovieByID: QueryUseCaseWithParam<String, Movie?> = QueryMovieByID(movieRepository)
}
