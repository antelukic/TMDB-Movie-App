package com.lukic.movieapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lukic.data.repository.DAY_TIME_WINDOW
import com.lukic.data.repository.WEEK_TIME_WINDOW
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.ShowType
import com.lukic.movieapp.R

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val trendingState = homeViewModel.trendingUIState.collectAsState()
    val forYouState = homeViewModel.forYouUIState.collectAsState()
    val discoverState = homeViewModel.discoverUIState.collectAsState()
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.size(size = 20.dp))
        Text(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.home_foryou_margin_start),
                    end = dimensionResource(id = R.dimen.home_foryou_margin_end)
                ),
            text = stringResource(id = R.string.for_you),
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.home_text_size).value.sp,
                color = colorResource(id = R.color.dark_blue_primary),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.proxima_nova_alt_bold, FontWeight.Bold))
            ),
        )
        Spacer(modifier = Modifier.size(8.dp))
        HomeTabs(
            modifier = Modifier.padding(start = 20.dp),
            tabs = listOf(
                stringResource(id = R.string.top_rated),
                stringResource(id = R.string.popular)
            ),
            onTabClick = { index ->
                homeViewModel.refreshForYouMovies(
                    if (index == 0) ForYouType.TOP_RATED
                    else ForYouType.POPULAR
                )
            }
        )
        Spacer(modifier = Modifier.size(24.dp))
        HomeMovies(
            modifier = Modifier
                .padding(start = 20.dp),
            movies = forYouState.value,
            onFavouriteSelectorClick = { movie ->
                homeViewModel.refreshFavouriteMovies(movie)
            },
            onImageClick = { id ->
                onImageClick(id)
            }
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.home_text_margin_top)))
        Text(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.home_foryou_margin_start),
                    end = dimensionResource(id = R.dimen.home_foryou_margin_end)
                ),
            text = stringResource(id = R.string.discover),
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.home_text_size).value.sp,
                color = colorResource(id = R.color.dark_blue_primary),
                fontFamily = FontFamily(Font(R.font.proxima_nova_alt_bold, FontWeight.Bold))
            ),
        )
        Spacer(modifier = Modifier.size(8.dp))
        HomeTabs(
            modifier = Modifier.padding(start = 20.dp),
            tabs = listOf(
                stringResource(id = R.string.now_playing),
                stringResource(id = R.string.upcoming)
            ),
            onTabClick = { index ->
                homeViewModel.refreshDiscoverMovies(
                    if (index == 0) ShowType.NOW_PLAYING
                    else ShowType.UPCOMING
                )
            }
        )
        Spacer(modifier = Modifier.size(24.dp))
        HomeMovies(
            modifier = Modifier
                .padding(start = 20.dp),
            movies = discoverState.value,
            onFavouriteSelectorClick = { movie ->
                homeViewModel.refreshFavouriteMovies(movie)
            },
            onImageClick = { id ->
                onImageClick(id)
            }
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.home_text_margin_top)))
        Text(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.home_foryou_margin_start),
                    end = dimensionResource(id = R.dimen.home_foryou_margin_end)
                ),
            text = stringResource(id = R.string.trending),
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.home_text_size).value.sp,
                color = colorResource(id = R.color.dark_blue_primary),
                fontFamily = FontFamily(Font(R.font.proxima_nova_alt_bold, FontWeight.Bold))
            ),
        )
        Spacer(modifier = Modifier.size(8.dp))
        HomeTabs(
            modifier = Modifier.padding(start = 20.dp),
            tabs = listOf(
                stringResource(id = R.string.today),
                stringResource(id = R.string.this_week)
            ),
            onTabClick = { index ->
                homeViewModel.refreshTrendingMovies(
                    if (index == 0) DAY_TIME_WINDOW
                    else WEEK_TIME_WINDOW
                )
            }
        )
        Spacer(modifier = Modifier.size(24.dp))
        HomeMovies(
            modifier = Modifier
                .padding(start = 20.dp),
            movies = trendingState.value,
            onFavouriteSelectorClick = { movie ->
                homeViewModel.refreshFavouriteMovies(movie)
            },
            onImageClick = { id ->
                onImageClick(id)
            }
        )
        Spacer(modifier = Modifier.size(48.dp))
    }
}
