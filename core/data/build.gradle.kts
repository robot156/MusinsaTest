plugins {
    alias(libs.plugins.musinsa.android.library)
    alias(libs.plugins.musinsa.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.minjin.musinsa.data"

    defaultConfig {
        buildConfigField("String", "API_BASE_URL", properties["API_BASE_URL"] as String)
    }
}

dependencies {
    implementation(projects.core.domain)

    // Kotlin
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Log tracker
    implementation(libs.timber)
}