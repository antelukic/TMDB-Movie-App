package com.lukic.movieapp.ui.home

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.lukic.movieapp.R
import com.lukic.movieapp.utils.sharedcomposables.MovieBox
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeMovies(
    movies: ImmutableList<HomeMovieUIState>,
    onFavouriteSelectorClick: (HomeMovieUIState) -> Unit,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
    ) {
        items(items = movies) { movie: HomeMovieUIState ->
            MovieBox(
                posterPath = movie.posterPath,
                isFavourite = movie.isFavourite,
                onImageClick = { onImageClick(movie.movieID) },
                onFavouriteSelectorClick = { onFavouriteSelectorClick(movie) },
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.movie_image_height))
                    .width(dimensionResource(id = R.dimen.movie_image_width))
                    .padding(end = dimensionResource(id = R.dimen.image_margin_end))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.common_card_radius)))
            )
        }
    }
}
