plugins {
    alias(libs.plugins.musinsa.jvm.library)
    alias(libs.plugins.musinsa.jvm.hilt)
}

dependencies {
    // Kotlin
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines)
}