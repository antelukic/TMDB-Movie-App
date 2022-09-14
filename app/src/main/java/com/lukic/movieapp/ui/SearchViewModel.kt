package com.lukic.movieapp.ui

import androidx.lifecycle.ViewModel
import com.lukic.domain.model.Movie
import com.lukic.domain.usecase.QuerySearchMovies
import com.lukic.domain.usecase.RefreshSearchMovies
import com.lukic.movieapp.BuildConfig
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

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
