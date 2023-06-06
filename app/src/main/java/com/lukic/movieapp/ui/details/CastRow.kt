package com.lukic.movieapp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lukic.domain.model.Cast
import com.lukic.movieapp.R
import com.lukic.movieapp.utils.tmbdImage
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CastRow(
    cast: ImmutableList<Cast>,
    modifier: Modifier = Modifier,
    spacedBy: Dp = 4.dp,
    cardElevation: Dp = 4.dp,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(spacedBy)
    ) {
        items(cast) { castMember ->
            CastMember(
                castMember = castMember,
                cardElevation = cardElevation
            )
        }
    }
}

@Composable
fun CastMember(
    castMember: Cast,
    modifier: Modifier = Modifier,
    cardElevation: Dp = 4.dp,
) {
    Card(
        modifier = modifier
            .size(
                width = dimensionResource(id = R.dimen.cast_card_width),
                height = dimensionResource(id = R.dimen.cast_card_height)
            ),
        elevation = cardElevation
    ) {
        Column {
            val image = tmbdImage(posterPath = castMember.posterPath)
            if (image is Painter) {
                Image(
                    painter = image,
                    contentDescription = stringResource(R.string.cast_member)
                )
            } else {
                AsyncImage(
                    model = image,
                    contentDescription = stringResource(R.string.cast_member),
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.cast_image_height))
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = castMember.name,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.cast_name_padding_start),
                        end = dimensionResource(id = R.dimen.cast_name_padding_start),
                        top = dimensionResource(id = R.dimen.cast_name_padding_top)
                    ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = castMember.name,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.cast_name_padding_start),
                        vertical = dimensionResource(id = R.dimen.cast_role_name_padding_bottom)
                    ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
