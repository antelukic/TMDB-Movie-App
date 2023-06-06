package com.lukic.movieapp.ui.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.lukic.movieapp.R
import kotlinx.collections.immutable.ImmutableList

@Composable
fun FavouritesScreen(
    movies: ImmutableList<FavouritesUIState>,
    onFavouriteSelectorClick: (FavouritesUIState) -> Unit,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.favourites_top_spacer)))
        Text(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.favourites_text_padding_start)
                ),
            text = stringResource(id = R.string.favourites),
            style = MaterialTheme.typography.h6
        )
        FavouriteMovies(
            movies = movies,
            onFavouriteSelectorClick = onFavouriteSelectorClick,
            onImageClick = onImageClick,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.favourites_movies_horizontal_padding),
                top = dimensionResource(id = R.dimen.favourites_movies_padding_top),
                end = dimensionResource(id = R.dimen.favourites_movies_horizontal_padding)
            )
        )
    }
}
