package com.lukic.movieapp.di

import com.lukic.movieapp.data.FakeMovieRepositoryImpl
import com.lukic.movieapp.domain.repository.MovieRepository
import com.lukic.movieapp.domain.usecase.QueryAllMovies
import com.lukic.movieapp.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<MovieRepository> { FakeMovieRepositoryImpl() }
    single { QueryAllMovies(get()) }

    viewModel { HomeViewModel(get()) }
}
