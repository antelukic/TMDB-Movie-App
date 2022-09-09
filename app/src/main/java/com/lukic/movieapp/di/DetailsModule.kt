package com.lukic.movieapp.di

import com.lukic.domain.usecase.QueryMovieDetails
import com.lukic.domain.usecase.RefreshMovieDetails
import com.lukic.movieapp.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    single { QueryMovieDetails(get()) }
    single { RefreshMovieDetails(get()) }

    viewModel { parameters ->
        DetailsViewModel(
            get(),
            get(),
            get(),
            get(),
            movieID = parameters.get()
        )
    }
}
