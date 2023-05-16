plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
}

android {
    compileSdk = AppConfig.compileSdk
}

dependencies {
    implementation(project(AppConfig.domainModule))

    implementation(Dependencies.ktorCore)
    implementation(Dependencies.ktorCio)
    implementation(Dependencies.ktorLogging)
    implementation(Dependencies.ktorClientSerialization)
    implementation(Dependencies.ktorSerializationJson)
    implementation(Dependencies.ktorContentNegotiation)

    implementation(Dependencies.serialization)

    implementation(Dependencies.koin)

    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)

}
