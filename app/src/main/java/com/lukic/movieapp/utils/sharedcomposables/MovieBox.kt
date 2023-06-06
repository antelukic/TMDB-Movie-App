package com.lukic.movieapp.utils.sharedcomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.lukic.movieapp.R
import com.lukic.movieapp.utils.tmbdImage

@Composable
fun MovieBox(
    posterPath: String,
    isFavourite: Boolean,
    onImageClick: () -> Unit,
    onFavouriteSelectorClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .clickable { onImageClick() },
            model = tmbdImage(posterPath),
            contentDescription = stringResource(R.string.movie),
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.image_margin_all))
                .clickable { onFavouriteSelectorClick() },
            painter = if (isFavourite) {
                painterResource(id = R.drawable.ic_filled_heart_with_background)
            } else {
                painterResource(id = R.drawable.ic_heart)
            },
            contentDescription = stringResource(R.string.movie_favourite_selector)
        )
    }
}
