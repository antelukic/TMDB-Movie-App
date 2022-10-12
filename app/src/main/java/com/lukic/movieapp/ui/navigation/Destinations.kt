package com.lukic.movieapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations {
    val icon: ImageVector
    val route: String
    val text: String
}

object Home : Destinations {
    override val icon: ImageVector = Icons.Filled.Home
    override val route: String = "home"
    override val text: String = "Home"
}

object Search : Destinations {
    override val icon: ImageVector = Icons.Filled.Search
    override val route: String = "search"
    override val text: String = "Search"
}

object Favourites : Destinations {
    override val icon: ImageVector = Icons.Filled.Favorite
    override val route: String = "favourites"
    override val text: String = "Favourites"
}

object Details : Destinations {
    override val icon: ImageVector = Icons.Default.Info
    override val route: String = "details"
    override val text: String = "Details"
    const val detailsTypeArg = "movie_id"
    val routeWithArgs = "$route/{$detailsTypeArg}"
    val argumens = listOf(
        navArgument(detailsTypeArg) { type = NavType.IntType }
    )
}

val movieBottomRowScreens = listOf(Home, Search, Favourites)
