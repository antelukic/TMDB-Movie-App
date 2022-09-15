package com.lukic.movieapp.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.lukic.movieapp.BuildConfig
import com.lukic.movieapp.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeMovies(
    movies: List<HomeMovieUIState>,
    onFavouriteSelectorClick: (HomeMovieUIState) -> Unit,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(items = movies) { movie: HomeMovieUIState ->
            Box {
                AsyncImage(
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.movie_image_height))
                        .width(dimensionResource(id = R.dimen.movie_image_width))
                        .padding(end = dimensionResource(id = R.dimen.image_margin_end))
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.common_card_radius)))
                        .clickable { onImageClick(movie.movieID) }
                        .animateItemPlacement(),
                    model = if (movie.posterPath.removePrefix(BuildConfig.DOMAIN_BASE_IMAGE)
                            .isEmpty()
                    )
                        painterResource(id = R.drawable.tmdb_logo)
                    else movie.posterPath,
                    contentDescription = stringResource(R.string.movie),
                    contentScale = if (movie.posterPath.removePrefix(BuildConfig.DOMAIN_BASE_IMAGE)
                            .isEmpty()
                    )
                        ContentScale.None
                    else ContentScale.Crop
                )
                Image(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.image_margin_all))
                        .clickable { onFavouriteSelectorClick(movie) },
                    painter = if (movie.isFavourite) painterResource(id = R.drawable.ic_filled_heart_with_background)
                    else painterResource(id = R.drawable.ic_heart),
                    contentDescription = stringResource(R.string.movie_favourite_selector)
                )
            }
        }
    }
}
