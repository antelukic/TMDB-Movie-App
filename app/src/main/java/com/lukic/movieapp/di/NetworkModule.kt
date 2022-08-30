package com.lukic.movieapp.di

import com.lukic.data.api.KtorClient
import com.lukic.data.api.MovieService
import com.lukic.data.api.MovieServiceImpl
import com.lukic.movieapp.BuildConfig
import org.koin.dsl.module

val networkModule = module {

    single { KtorClient(BuildConfig.DOMAIN_BASE, BuildConfig.API_KEY) }

    single<MovieService> { MovieServiceImpl(get()) }
}
