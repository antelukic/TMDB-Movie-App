package com.lukic.movieapp

import android.app.Application
import com.lukic.data.database.databaseModule
import com.lukic.movieapp.di.detailsModule
import com.lukic.movieapp.di.favouritesModule
import com.lukic.movieapp.di.homeModule
import com.lukic.movieapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    homeModule,
                    detailsModule,
                    networkModule,
                    favouritesModule,
                    databaseModule
                )
            )
        }
    }
}
