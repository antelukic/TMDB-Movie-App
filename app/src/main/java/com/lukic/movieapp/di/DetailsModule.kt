package com.lukic.movieapp.di

import com.lukic.movieapp.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    viewModel { DetailsViewModel() }
}
