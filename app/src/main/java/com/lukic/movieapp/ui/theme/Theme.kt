package com.lukic.movieapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColors(
    primary = Blue900Dark,
    onPrimary = Color.White,
    secondary = Gray300,
    secondaryVariant = Gray600,
    onSecondary = Color.Black,
    error = Color.Red
)

@Composable
fun MovieTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColors,
        typography = MovieTypography,
        shapes = MovieShapes,
        content = content
    )
}
