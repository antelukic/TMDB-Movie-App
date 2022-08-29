package com.lukic.movieapp.data.repository

import com.lukic.movieapp.data.api.MovieService
import com.lukic.movieapp.data.api.model.ForYouApi
import com.lukic.movieapp.data.api.model.ShowTypeApi
import com.lukic.movieapp.data.mapper.MovieMapper
import com.lukic.movieapp.domain.model.ForYouType
import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.model.ShowType
import com.lukic.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override suspend fun trendingMovies(timeWindow: String): List<Movie> =
        movieMapper.toMovies(movieService.fetchTrendingMovies(timeWindow).movies)

    override suspend fun discoverShows(showType: ShowType): List<Movie> =
        movieMapper.toMovies(movieService.fetchDiscoverShows(when(showType){
            ShowType.MOVIE -> ShowTypeApi.Movie
            ShowType.TV -> ShowTypeApi.Tv
        }).movies)

    override suspend fun forYouMovies(type: ForYouType): List<Movie> =
        movieMapper.toMovies(movieService.fetchForYouMovies(when(type) {
            ForYouType.POPULAR -> ForYouApi.Popular
            ForYouType.TOP_RATED -> ForYouApi.TopRated
        }).movies)
}
