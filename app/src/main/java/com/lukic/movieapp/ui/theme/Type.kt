package com.lukic.movieapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lukic.movieapp.R

private val ProximaNova = FontFamily(
    Font(R.font.proxima_nova_alt_bold, FontWeight.Bold),
    Font(R.font.proxima_nova_regular, FontWeight.Normal)
)

val MovieTypography = Typography(
    h6 = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = ProximaNova,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
)
