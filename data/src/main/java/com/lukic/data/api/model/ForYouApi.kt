package com.lukic.data.api.model

sealed class ForYouApi(val key: String) {
    object Popular : ForYouApi("popular")
    object TopRated : ForYouApi("top_rated")
}
