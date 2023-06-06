package com.lukic.movieapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.lukic.movieapp.R

private const val COLOR_ANIMATION_DURATION = 500
private const val SLIDE_OUT_ANIMATION_DURATION = 500
private const val SLIDE_IN_ANIMATION_DURATION = 500
private const val ENTER_ANIMATION_DURATION = 500
private const val INITIAL_OFFSET_DIVIDER = 3

@Composable
fun BottomNavTab(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
        animationSpec = tween(
            durationMillis = COLOR_ANIMATION_DURATION,
            easing = LinearEasing
        )
    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.bottom_nav_icon_padding))
            .animateContentSize()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = tabTintColor
        )
        AnimatedVisibility(
            visible = selected,
            enter = slideInHorizontally(animationSpec = tween(SLIDE_IN_ANIMATION_DURATION)) { fullWidth ->
                -fullWidth / INITIAL_OFFSET_DIVIDER
            } + fadeIn(
                animationSpec = tween(ENTER_ANIMATION_DURATION)
            ),
            exit = slideOutHorizontally(animationSpec = tween(SLIDE_OUT_ANIMATION_DURATION)) + fadeOut()
        ) {
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_width_bottom_nav)))
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.primary
            )
        }
    }
}
