package com.lukic.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lukic.movieapp.ui.details.DetailsScreen
import com.lukic.movieapp.ui.favourites.FavouritesScreen
import com.lukic.movieapp.ui.favourites.FavouritesViewModel
import com.lukic.movieapp.ui.home.HomeScreen
import com.lukic.movieapp.ui.search.SearchScreen
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private const val MOVIE_ID_DEFAULT_VALUE = 0

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(Home.route) {
            HomeScreen(onImageClick = { movieId ->
                navController.navigateToDetails(movieId)
            })
        }
        composable(Search.route) {
            SearchScreen(onItemClick = navController::navigateToDetails)
        }
        composable(Favourites.route) {
            val viewModel = koinViewModel<FavouritesViewModel>()
            FavouritesScreen(
                movies = viewModel.uiState.collectAsState().value.toImmutableList(),
                onFavouriteSelectorClick = viewModel::removeFavouriteMovie,
                onImageClick = navController::navigateToDetails
            )
        }
        composable(
            route = Details.routeWithArgs,
            arguments = Details.argumens
        ) { navBackStackEntry ->
            val movieId = navBackStackEntry.arguments?.getInt(Details.detailsTypeArg) ?: MOVIE_ID_DEFAULT_VALUE
            DetailsScreen(detailsViewModel = getViewModel(parameters = { parametersOf(movieId) }))
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navigateToDetails(movieId: Int) {
    this.navigateSingleTopTo("${Details.route}/$movieId")
}
