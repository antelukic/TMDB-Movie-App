package com.lukic.data.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(androidContext(), MovieDatabase::class.java, "movie-database")
            .fallbackToDestructiveMigration().build().dao
    }
}
