package com.lukic.movieapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukic.movieapp.domain.usecase.QueryMovieDetails
import kotlinx.coroutines.launch

private const val DEFAULT_MOVIE_ID = 616037

class DetailsViewModel(private val queryMovieDetails: QueryMovieDetails) : ViewModel() {

    var uiState: MovieDetailsUIState? = null

    init {
        getMovieByID(DEFAULT_MOVIE_ID)
    }

    private fun getMovieByID(id: Int) {
        viewModelScope.launch {
            uiState = queryMovieDetails(id).let {
                MovieDetailsUIState(
                    title = it.title,
                    overview = it.overview,
                    genres = it.genres,
                    rating = it.rating,
                    credits = it.crew,
                    releaseDate = it.releaseDate,
                    duration = it.duration,
                    cast = it.cast,
                    posterPath = it.posterPath
                )
            }
            Log.d("DetailsViewModel", "getMovieByID: detailsUIState $uiState")
        }
    }
}
