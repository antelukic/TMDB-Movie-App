package com.lukic.data.api.model

import com.lukic.data.api.model.ShowTypeApi.NowPlaying
import com.lukic.data.api.model.ShowTypeApi.Upcoming
import com.lukic.domain.model.ShowType
import com.lukic.domain.model.ShowType.NOW_PLAYING
import com.lukic.domain.model.ShowType.UPCOMING

sealed class ShowTypeApi(val key: String) {
    object NowPlaying : ShowTypeApi("now_playing")
    object Upcoming : ShowTypeApi("upcoming")
}

fun ShowType.toShowTypeApi(): ShowTypeApi =
    when (this) {
        NOW_PLAYING -> NowPlaying
        UPCOMING -> Upcoming
    }
