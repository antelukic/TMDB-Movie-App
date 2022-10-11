package com.lukic.movieapp.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.lukic.movieapp.R
import com.lukic.movieapp.ui.search.GradientImageWithContent
import com.lukic.movieapp.ui.search.MovieDetailsImageContent
import com.lukic.movieapp.ui.theme.MovieTheme
import kotlinx.collections.immutable.toImmutableList

@Suppress("LongMethod")
@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel,
    modifier: Modifier = Modifier,
) {
    MovieTheme {
        val uiState = detailsViewModel.uiState.collectAsState()
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.details_image_height))
            ) {
                GradientImageWithContent(
                    imagePosterPath = uiState.value.posterPath,
                    content = {
                        with(uiState.value) {
                            MovieDetailsImageContent(
                                title = title,
                                duration = duration,
                                genres = genres.toImmutableList(),
                                releaseDate = releaseDate,
                                rating = rating,
                                progress = progress,
                                isFavourite = isFavourite,
                                onFavouriteSelected = { detailsViewModel.refreshFavouriteMovies() },
                                modifier = Modifier.matchParentSize()
                            )
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
            Text(
                text = stringResource(id = R.string.overview),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(
                    vertical = dimensionResource(id = R.dimen.details_overview_padding_top),
                    horizontal = dimensionResource(id = R.dimen.common_text_padding_all)
                )
            )
            Text(
                text = uiState.value.overview,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(id = R.dimen.details_text_vertical_padding),
                        horizontal = dimensionResource(id = R.dimen.common_text_padding_all)
                    )
            )
            CrewMembersGrid(
                crew = uiState.value.crew.toImmutableList(),
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.details_crew_horizontal_padding),
                        end = dimensionResource(id = R.dimen.details_crew_horizontal_padding),
                        top = dimensionResource(id = R.dimen.details_crew_top_padding)
                    )
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.text_billed_cast_margin_top)))
            Text(
                text = stringResource(id = R.string.top_billed_cast),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.common_text_padding_all),
                    end = dimensionResource(id = R.dimen.common_text_padding_all),
                    top = dimensionResource(id = R.dimen.text_billed_cast_margin_top),
                    bottom = dimensionResource(id = R.dimen.text_billed_cast_padding_bottom)
                )
            )
            CastRow(
                cast = uiState.value.cast.toImmutableList(),
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.details_cast_rv_margin_start),
                    bottom = dimensionResource(id = R.dimen.cast_padding_bottom)
                ),
                spacedBy = dimensionResource(id = R.dimen.cast_items_spaced_by),
                cardElevation = dimensionResource(id = R.dimen.cast_card_elevation)
            )
        }
    }
}
