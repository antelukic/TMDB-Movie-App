package com.lukic.movieapp.di

import com.lukic.domain.usecase.QuerySearchMovies
import com.lukic.domain.usecase.RefreshSearchMovies
import com.lukic.movieapp.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    single { RefreshSearchMovies(get()) }
    single { QuerySearchMovies(get()) }

    viewModel { SearchViewModel(get(), get()) }
}
