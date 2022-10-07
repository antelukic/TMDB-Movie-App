object Dependencies {

    const val androidCore = "androidx.core:core-ktx:${Versions.Android.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
    const val material = "com.google.android.material:material:${Versions.Android.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
    const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.Android.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui:${Versions.Android.navigation}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.Compose.version}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.Compose.version}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.version}"
    const val composeCoil = "io.coil-kt:coil-compose:${Versions.Compose.coil}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    const val immutableCollections =
        "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.Kotlin.immutableCollections}"

    const val ktorCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorCio = "io.ktor:ktor-client-cio:${Versions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val ktorClientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"

    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"

    const val junit = "junit:junit:${Versions.Testing.junit}"
    const val testJunit = "androidx.test.ext:junit:${Versions.Testing.testJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Testing.espressoCore}"
}
