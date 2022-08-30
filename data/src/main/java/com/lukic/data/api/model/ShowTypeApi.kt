package com.lukic.data.api.model

sealed class ShowTypeApi(val key: String) {
    object Tv : ShowTypeApi("tv")
    object Movie : ShowTypeApi("movie")
}