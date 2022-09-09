package com.lukic.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DbMovie::class],
    version = 2
)

abstract class MovieDatabase : RoomDatabase() {
    abstract val dao: MovieDao
}
