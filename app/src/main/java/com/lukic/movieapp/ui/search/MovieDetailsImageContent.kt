package com.lukic.movieapp.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.lukic.movieapp.R
import kotlinx.collections.immutable.ImmutableList

@Suppress("LongMethod")
@Composable
fun MovieDetailsImageContent(
    title: String,
    duration: String,
    genres: ImmutableList<String>,
    releaseDate: String,
    rating: Int,
    progress: Float,
    isFavourite: Boolean,
    onFavouriteSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.details_image_top_spacer_height)))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressBar(
                percentage = progress,
                number = rating,
                textColor = MaterialTheme.colors.onPrimary,
                textStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.details_progress_indicator_margin_start))
            )
            Text(
                text = stringResource(id = R.string.user_score),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.common_text_padding))
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.details_title_text_margin_end),
                vertical = dimensionResource(id = R.dimen.common_text_padding)
            )
        )
        Text(
            text = releaseDate,
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal),
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
                top = 4.dp,
                bottom = 4.dp
            )
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal).toSpanStyle()) {
                    genres.forEachIndexed { index, genre ->
                        if (index == 0) {
                            append(genre)
                        } else {
                            append(", $genre")
                        }
                    }
                }
                withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                    append(" $duration")
                }
            },
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
                top = 4.dp,
                bottom = 4.dp
            )
        )
        Box(modifier = Modifier.weight(1f)) {
            Image(
                painter = if (isFavourite) {
                    painterResource(id = R.drawable.ic_filled_heart_with_background)
                } else {
                    painterResource(
                        id = R.drawable.ic_heart
                    )
                },
                contentDescription = stringResource(id = R.string.movie_favourite_selector),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        start = dimensionResource(id = R.dimen.favourite_selector_margin_start),
                        bottom = dimensionResource(id = R.dimen.favourite_selector_margin_bottom)
                    )
                    .clickable { onFavouriteSelected() }
            )
        }
    }
}
