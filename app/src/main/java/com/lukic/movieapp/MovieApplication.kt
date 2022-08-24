package com.lukic.movieapp

import android.app.Application
import com.lukic.movieapp.di.detailsModule
import com.lukic.movieapp.di.homeModule
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    homeModule,
                    detailsModule
                )
            )
        }
    }
}
