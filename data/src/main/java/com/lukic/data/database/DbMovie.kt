package com.lukic.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie-table")
data class DbMovie(
    @PrimaryKey val id: Int,
    val posterPath: String,
)
