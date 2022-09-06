package com.lukic.data.api

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
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
