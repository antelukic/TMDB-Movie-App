package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.domain.model.Movie
import com.lukic.domain.usecase.QuerySearchMovies
import com.lukic.domain.usecase.RefreshSearchMovies
import com.lukic.movieapp.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val SEARCH_DEBOUNCE_TIME = 500L

@OptIn(FlowPreview::class)
class SearchViewModel(
    querySearchMovies: QuerySearchMovies,
    private val refreshSearchMovies: RefreshSearchMovies
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<SearchUIState>>(emptyList())
    val uiState: StateFlow<List<SearchUIState>> get() = _uiState

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val searchQueryPublisher = MutableSharedFlow<String>()

    init {
        scope.launch {
            searchQueryPublisher
                .debounce(SEARCH_DEBOUNCE_TIME)
                .collect { query ->
                    if (query.isNotEmpty()) {
                        refreshSearchMovies(query = query)
                    }
                }
        }

        querySearchMovies()
            .onEach { movies -> _uiState.update { fromMoviesToSearchUIState(movies) } }
            .launchIn(scope)
    }

    fun refreshSearchMovies(searchText: String) {
        scope.launch {
            searchQueryPublisher.emit(searchText)
        }
    }

    private fun fromMoviesToSearchUIState(movies: List<Movie>): List<SearchUIState> =
        movies.map { movie ->
            with(movie) {
                SearchUIState(
                    movieId = id,
                    posterPath = BuildConfig.DOMAIN_BASE_IMAGE + posterPath,
                    title = title,
                    description = overview
                )
            }
        }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
