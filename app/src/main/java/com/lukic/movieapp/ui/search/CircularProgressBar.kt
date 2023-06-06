@file:Suppress("MagicNumber")

package com.lukic.movieapp.ui.search

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lukic.movieapp.R
import com.lukic.movieapp.ui.theme.Green900
import com.lukic.movieapp.ui.theme.GreenA400Light

@Composable
fun CircularProgressBar(
    percentage: Float, // Needs to be from 0 to 1
    number: Int,
    modifier: Modifier = Modifier,
    radius: Dp = 24.dp,
    strokeWidth: Dp = 3.dp,
    animationDuration: Int = 1000,
    textColor: Color = MaterialTheme.colors.secondary,
    textStyle: TextStyle = MaterialTheme.typography.h6
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(animationDuration)
    )
    val animatedColor by animateColorAsState(
        targetValue = progressBarColor(number = number),
        animationSpec = tween(2000)
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier.size(radius * 2f)
        ) {
            drawArc(
                color = animatedColor,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = stringResource(id = R.string.movie_rating, number.toString()),
            color = textColor,
            style = textStyle
        )
    }
}

@Composable
private fun progressBarColor(number: Int): Color =
    if (number < 33.3) {
        Color.Yellow
    } else if (number < 66.6) {
        Green900
    } else {
        GreenA400Light
    }
