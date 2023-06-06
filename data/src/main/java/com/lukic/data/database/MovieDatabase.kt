package com.lukic.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DbMovie::class],
    version = 2,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val dao: MovieDao
}
