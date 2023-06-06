package com.lukic.movieapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.lukic.movieapp.BuildConfig
import com.lukic.movieapp.R

@Composable
fun tmbdImage(posterPath: String): Any =
    if (posterPath.removePrefix(BuildConfig.DOMAIN_BASE_IMAGE).isEmpty()) {
        painterResource(id = R.drawable.tmdb_logo)
    } else {
        posterPath
    }
