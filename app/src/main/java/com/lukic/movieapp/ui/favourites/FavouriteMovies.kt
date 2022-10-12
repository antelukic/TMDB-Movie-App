package com.lukic.movieapp.ui.favourites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.lukic.movieapp.R
import com.lukic.movieapp.ui.components.MovieBox
import kotlinx.collections.immutable.ImmutableList

private const val GRID_CELLS = 3

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteMovies(
    movies: ImmutableList<FavouritesUIState>,
    onFavouriteSelectorClick: (FavouritesUIState) -> Unit,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(GRID_CELLS),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.favourites_movies_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.favourites_image_vertical_padding))
    ) {
        items(movies, key = { it.movieID }) { movie ->
            MovieBox(
                posterPath = movie.posterPath,
                isFavourite = true,
                onImageClick = { onImageClick(movie.movieID) },
                onFavouriteSelectorClick = { onFavouriteSelectorClick(movie) },
                modifier = Modifier
                    .animateItemPlacement()
                    .size(
                        width = dimensionResource(id = R.dimen.favourites_image_width),
                        height = dimensionResource(id = R.dimen.favourites_image_height)
                    )
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.common_card_radius)))
            )
        }
    }
}
