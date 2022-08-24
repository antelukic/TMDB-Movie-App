package com.lukic.movieapp.di

import com.lukic.movieapp.domain.usecase.QueryMovieByID
import com.lukic.movieapp.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    single { QueryMovieByID(get()) }

    viewModel { DetailsViewModel(get()) }
}
