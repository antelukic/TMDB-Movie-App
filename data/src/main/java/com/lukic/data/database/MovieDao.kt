package com.lukic.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM `movie-table`")
    fun fetchMovies(): Flow<List<DbMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: DbMovie)

    @Delete
    suspend fun deleteMovie(movie: DbMovie)
}
