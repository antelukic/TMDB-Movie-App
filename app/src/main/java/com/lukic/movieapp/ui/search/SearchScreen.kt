package com.lukic.movieapp.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.lukic.movieapp.R
import com.lukic.movieapp.ui.components.MovieTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = koinViewModel()
) {
    val searchText = searchViewModel.searchText.collectAsState()
    val searchUIState = searchViewModel.uiState.collectAsState()
    LazyColumn(
        modifier = modifier
    ) {
        item {
            MovieTextField(
                text = searchText.value,
                onSearchTextChange = searchViewModel::refreshSearchMovies,
                onClearValue = { searchViewModel.refreshSearchMovies("") },
                placeholderText = stringResource(id = R.string.search),
                placeHolderTextColor = MaterialTheme.colors.secondaryVariant,
                backgroundColor = MaterialTheme.colors.secondary,
                labelTextStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.W100),
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(id = R.dimen.common_text_padding_all),
                        horizontal = dimensionResource(id = R.dimen.common_text_padding_all)
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxWidth()
            )
        }
        items(searchUIState.value) { movie ->
            SearchMovieItem(
                movie = movie,
                onItemClick = onItemClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimensionResource(id = R.dimen.search_movies_padding_bottom),
                        start = dimensionResource(id = R.dimen.search_movies_horizontal_margin),
                        end = dimensionResource(id = R.dimen.search_movies_horizontal_margin)
                    ),
                cardElevation = dimensionResource(id = R.dimen.search_card_elevation)
            )
        }
    }
}
