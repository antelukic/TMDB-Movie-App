package com.lukic.movieapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.lukic.movieapp.R
import com.lukic.movieapp.utils.tmbdImage

@Composable
fun GradientImageWithContent(
    imagePosterPath: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.details_image_height))
        ) {
            AsyncImage(
                model = tmbdImage(posterPath = imagePosterPath),
                contentDescription = stringResource(id = R.string.movie_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 0.3f
                        )
                    )
            )
            content()
        }
    }
}
