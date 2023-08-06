package com.lukic.data.api.model

import com.lukic.data.api.model.ForYouApi.Popular
import com.lukic.data.api.model.ForYouApi.TopRated
import com.lukic.domain.model.ForYouType
import com.lukic.domain.model.ForYouType.POPULAR
import com.lukic.domain.model.ForYouType.TOP_RATED

sealed class ForYouApi(val key: String) {
    object Popular : ForYouApi("popular")
    object TopRated : ForYouApi("top_rated")
}

fun ForYouType.toForYouApi(): ForYouApi =
    when (this) {
        POPULAR -> Popular
        TOP_RATED -> TopRated
    }
