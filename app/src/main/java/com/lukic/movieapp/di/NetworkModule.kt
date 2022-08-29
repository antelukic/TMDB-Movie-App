package com.lukic.movieapp.di

import com.lukic.movieapp.data.api.KtorClient
import com.lukic.movieapp.data.api.MovieService
import com.lukic.movieapp.data.api.MovieServiceImpl
import org.koin.dsl.module

val networkModule = module {

    single { KtorClient() }

    single<MovieService> { MovieServiceImpl(get()) }
}
