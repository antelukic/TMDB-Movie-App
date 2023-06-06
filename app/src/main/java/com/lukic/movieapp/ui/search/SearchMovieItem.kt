package com.lukic.movieapp.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lukic.movieapp.R
import com.lukic.movieapp.utils.tmbdImage

@Composable
fun SearchMovieItem(
    movie: SearchUIState,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    titleTextStyle: TextStyle = MaterialTheme.typography.h6,
    titleTextColor: Color = MaterialTheme.colors.primary,
    titleMaxLines: Int = 1,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.body1,
    descriptionTextColor: Color = MaterialTheme.colors.secondaryVariant,
    descriptionMaxLines: Int = 5,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    cardElevation: Dp = 0.dp
) {
    Card(
        modifier = modifier
            .clickable { onItemClick(movie.movieId) },
        elevation = cardElevation
    ) {
        Row {
            AsyncImage(
                model = tmbdImage(posterPath = movie.posterPath),
                contentDescription = stringResource(id = R.string.movie_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(
                        width = dimensionResource(id = R.dimen.search_image_width),
                        height = dimensionResource(id = R.dimen.search_image_height)
                    )
            )
            Box {
                Column {
                    Text(
                        text = movie.title,
                        style = titleTextStyle,
                        color = titleTextColor,
                        overflow = textOverflow,
                        maxLines = titleMaxLines,
                        modifier = Modifier
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.common_text_horizontal_padding),
                                vertical = dimensionResource(id = R.dimen.common_text_padding)
                            )
                    )
                    Text(
                        text = movie.description,
                        style = descriptionTextStyle,
                        color = descriptionTextColor,
                        overflow = textOverflow,
                        maxLines = descriptionMaxLines,
                        modifier = Modifier
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.common_text_horizontal_padding),
                                vertical = dimensionResource(id = R.dimen.common_text_padding)
                            )
                    )
                }
            }
        }
    }
}
