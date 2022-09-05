package com.lukic.data.api.model

sealed class ShowTypeApi(val key: String) {
    object NowPlaying : ShowTypeApi("now_playing")
    object Upcoming : ShowTypeApi("upcoming")
}
