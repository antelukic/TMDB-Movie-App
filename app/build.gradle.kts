plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

apply(from = "$rootDir/staticAnalysis/staticAnalysis.gradle")

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testIntrumentationRunner

        buildConfigField("String", "DOMAIN_BASE", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "DOMAIN_BASE_IMAGE", "\"https://image.tmdb.org/t/p/w500\"")
        buildConfigField("String", "API_KEY", "\"c6ea94caebf44cba907e6aa90ae307fe\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }

    buildFeatures {
        viewBinding = true
        compose = true

    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(Dependencies.androidCore)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
    implementation(Dependencies.composeBom)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.composeMaterial)

    implementation(Dependencies.immutableCollections)

    implementation(Dependencies.composeCoil)
    implementation(Dependencies.coil)

    implementation(Dependencies.koinCompose)

    implementation(project(AppConfig.domainModule))
    implementation(project(AppConfig.dataModule))

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.testJunit)
    androidTestImplementation(Dependencies.espressoCore)
}
