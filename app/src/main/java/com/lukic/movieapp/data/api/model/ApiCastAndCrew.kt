package com.lukic.movieapp.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCastAndCrew(
    @SerialName("cast")
    val cast: List<ApiCast>,
    @SerialName("crew")
    val crew: List<ApiCrew>,
)

@Serializable
data class ApiCast(
    @SerialName("id")
    val id: Int,
    @SerialName("character")
    val character: String,
    @SerialName("name")
    val name: String,
    @SerialName("profile_path")
    val posterPath: String?
)

@Serializable
data class ApiCrew(
    @SerialName("job")
    val job: String,
    @SerialName("name")
    val name: String,
)
