package com.lukic.movieapp.di

import com.lukic.movieapp.domain.usecase.QueryMovieDetails
import com.lukic.movieapp.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    single { QueryMovieDetails(get()) }

    viewModel { DetailsViewModel(get()) }
}
