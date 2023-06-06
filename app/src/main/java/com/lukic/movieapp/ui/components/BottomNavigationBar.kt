package com.lukic.movieapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lukic.movieapp.ui.navigation.movieBottomRowScreens

@Composable
fun BottomNavigationBar(
    route: String,
    onRouteSelected: (targetRoute: String) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        movieBottomRowScreens.forEach { destination ->
            val selected = route.contains(destination.route)
            BottomNavigationItem(
                icon = {
                    BottomNavTab(
                        icon = destination.icon,
                        selected = selected,
                        title = destination.text,
                        modifier = Modifier.weight(1f)
                    )
                },
                selected = selected,
                onClick = { onRouteSelected(destination.route) }
            )
        }
    }
}
