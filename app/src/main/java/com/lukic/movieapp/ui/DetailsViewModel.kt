package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel

class DetailsViewModel() : ViewModel() {

    var uiState: MovieDetailsUIState? = null

    fun getMovieByID(id: String) {}
}
