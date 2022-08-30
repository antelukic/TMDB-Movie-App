package com.lukic.data.repository

import com.lukic.data.api.MovieService
import com.lukic.data.mapper.MovieMapper
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.Movie
import com.lukic.domain.model.ShowType
import com.lukic.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override suspend fun trendingMovies(timeWindow: String): List<Movie> =
        movieMapper.toMovies(movieService.fetchTrendingMovies(timeWindow)?.movies)

    override suspend fun discoverShows(showType: ShowType): List<Movie> =
        movieMapper.toMovies(movieService.fetchDiscoverShows(movieMapper.toShowTypeApi(showType))?.movies)

    override suspend fun forYouMovies(type: ForYouType): List<Movie> =
        movieMapper.toMovies(movieService.fetchForYouMovies(movieMapper.toForYouApi(type))?.movies)

    override suspend fun movieDetails(movieId: Int): Movie {
        val detailsResponse = movieService.fetchMovieDetails(movieId)
        val castAndCrewResponse = movieService.fetchCastAndCrew(movieId)
        return movieMapper.toMovie(detailsResponse, castAndCrewResponse)
    }
}
