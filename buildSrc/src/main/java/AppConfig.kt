import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.invoke

object AppConfig {
    const val compileSdk = 32
    const val applicationId = "com.lukic.movieapp"
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0"
    const val jvmTarget = "1.8"
    const val kotlinCompilerExtensionVersion = "1.3.1"

    const val testIntrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val domainModule = ":domain"
    const val dataModule = ":data"
}
