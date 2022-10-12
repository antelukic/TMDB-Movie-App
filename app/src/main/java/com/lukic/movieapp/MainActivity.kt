package com.lukic.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lukic.movieapp.ui.MovieApp
import com.lukic.movieapp.ui.theme.MovieTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                MovieApp()
            }
        }
    }
}
