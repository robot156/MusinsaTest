plugins {
    alias(libs.plugins.musinsa.android.library)
    alias(libs.plugins.musinsa.android.library.compose)
}

android {
    namespace = "com.minjin.musinsa.designsystem"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)

    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.bundles.coil)
    implementation(libs.timber)

    api(libs.androidx.compose.ui.tooling)
}