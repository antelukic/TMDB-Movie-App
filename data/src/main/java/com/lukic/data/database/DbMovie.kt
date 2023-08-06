package com.lukic.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lukic.data.api.model.EMPTY
import com.lukic.domain.model.Movie

@Entity(tableName = "movie-table")
data class DbMovie(
    @PrimaryKey val id: Int,
    val posterPath: String,
)


fun List<DbMovie>?.toFavouriteMovies() =
    this?.map { dbMovie ->
        with(dbMovie) {
            Movie(
                id = this.id,
                title = EMPTY,
                overview = EMPTY,
                rating = 0,
                genres = emptyList(),
                releaseDate = EMPTY,
                duration = EMPTY,
                cast = emptyList(),
                posterPath = posterPath,
                crew = emptyList(),
                isFavourite = true
            )
        }
    } ?: emptyList()

fun Movie.toDbMovie(): DbMovie =
    with(this) {
        DbMovie(
            id = id,
            posterPath = posterPath
        )
    }
