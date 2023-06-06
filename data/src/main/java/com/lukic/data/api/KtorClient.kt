package com.lukic.data.api

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json

class KtorClient(
    private val domainBase: String,
    private val apiKey: String
) {

    val client = HttpClient(CIO) {

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("HTTP", message)
                }
            }
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            val converter = KotlinxSerializationConverter(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
            register(ContentType.Application.Json, converter)
        }

        defaultRequest {
            url(domainBase)
            url.parameters.append("api_key", apiKey)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}
