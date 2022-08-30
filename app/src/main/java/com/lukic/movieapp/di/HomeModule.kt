package com.lukic.movieapp.di

import com.lukic.data.mapper.MovieMapper
import com.lukic.data.mapper.MovieMapperImpl
import com.lukic.data.repository.MovieRepositoryImpl
import com.lukic.domain.repository.MovieRepository
import com.lukic.domain.usecase.QueryDiscoverShows
import com.lukic.domain.usecase.QueryForYouMovies
import com.lukic.domain.usecase.QueryTrendingMovies
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
