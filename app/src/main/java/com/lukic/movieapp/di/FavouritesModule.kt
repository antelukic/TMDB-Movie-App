package com.lukic.movieapp.di

import com.lukic.domain.usecase.AddFavouriteMovie
import com.lukic.domain.usecase.QueryFavouriteMovies
import com.lukic.domain.usecase.RemoveFavouriteMovie
import com.lukic.movieapp.ui.FavouritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouritesModule = module {

    single { QueryFavouriteMovies(get()) }
    single { RemoveFavouriteMovie(get()) }
    single { AddFavouriteMovie(get()) }

    viewModel { FavouritesViewModel(get(), get()) }
}
