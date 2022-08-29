package com.lukic.movieapp.di

import com.lukic.movieapp.data.mapper.MovieMapper
import com.lukic.movieapp.data.mapper.MovieMapperImpl
import com.lukic.movieapp.data.repository.MovieRepositoryImpl
import com.lukic.movieapp.domain.repository.MovieRepository
import com.lukic.movieapp.domain.usecase.QueryDiscoverShows
import com.lukic.movieapp.domain.usecase.QueryForYouMovies
import com.lukic.movieapp.domain.usecase.QueryTrendingMovies
import com.lukic.movieapp.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<MovieMapper> { MovieMapperImpl() }

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }

    single { QueryForYouMovies(get()) }
    single { QueryDiscoverShows(get()) }
    single { QueryTrendingMovies(get()) }

    viewModel { HomeViewModel(get(), get(), get()) }
}
