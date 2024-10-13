plugins {
    alias(libs.plugins.musinsa.android.application)
    alias(libs.plugins.musinsa.android.application.compose)
    alias(libs.plugins.musinsa.android.hilt)
}

android {
    namespace = "com.minjin.musinsa"

    buildTypes {

        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(projects.core.model)

    implementation(projects.feature.main)
    implementation(projects.feature.list)

    // AndroidX
    implementation(libs.androidx.startup)

    // Kotlin
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines)

    // Coil
    implementation(libs.bundles.coil)

    // Log tracker
    implementation(libs.timber)
}