plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = AppConfig.compileSdk
}

dependencies {
    api(Dependencies.coroutines)
}
