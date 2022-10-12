package com.lukic.movieapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lukic.movieapp.ui.components.BottomNavigationBar
import com.lukic.movieapp.ui.components.MovieTopAppBar
import com.lukic.movieapp.ui.navigation.Details
import com.lukic.movieapp.ui.navigation.Home
import com.lukic.movieapp.ui.navigation.Navigation

private const val TWEEN_VISIBILITY_ANIMATION_DURATION = 500

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route ?: Home.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            AnimatedVisibility(
                visible = route != Details.routeWithArgs,
                enter = slideInVertically(
                    animationSpec = tween(TWEEN_VISIBILITY_ANIMATION_DURATION, easing = LinearEasing)
                ) + fadeIn(),
                exit = slideOutVertically(
                    animationSpec = tween(TWEEN_VISIBILITY_ANIMATION_DURATION, easing = LinearEasing)
                ) + fadeOut()
            ) {
                BottomNavigation(
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    BottomNavigationBar(
                        route = route,
                        onRouteSelected = { targetRoute ->
                            navController.apply {
                                navigate(targetRoute) {
                                    restoreState = true
                                    launchSingleTop = true
                                    graph.startDestinationRoute?.let { route ->
                                        popUpTo(route = route) {
                                            saveState = true
                                        }
                                    }
                                    popBackStack()
                                }
                            }
                        }
                    )
                }
            }
        },
        topBar = {
            MovieTopAppBar(
                currentScreenRoute = route,
                onArrowBackClicked = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->
        Navigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
