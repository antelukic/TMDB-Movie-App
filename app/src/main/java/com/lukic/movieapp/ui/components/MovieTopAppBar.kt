package com.lukic.movieapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.lukic.movieapp.R
import com.lukic.movieapp.ui.navigation.Details
import com.lukic.movieapp.ui.theme.TopAppBarColor

@Composable
fun MovieTopAppBar(
    currentScreenRoute: String?,
    onArrowBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.header_height))
            .background(TopAppBarColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.tmdb_logo),
                contentDescription = stringResource(id = R.string.tmdb_logo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.tmdb_logo_width))
                    .height(dimensionResource(id = R.dimen.tmdb_logo_height))
                    .align(Alignment.Center)
            )
        }
        if (currentScreenRoute == Details.routeWithArgs) {
            IconButton(
                onClick = { onArrowBackClicked() },
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.arrow_back_padding))
                    .size(dimensionResource(id = R.dimen.arrow_back_size))
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.navigate_back),
                    modifier = Modifier
                        .matchParentSize()
                        .align(Alignment.CenterStart),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}
