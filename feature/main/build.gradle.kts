plugins {
    alias(libs.plugins.musinsa.android.feature)
}

android {
    namespace = "com.minjin.musinsa.main"
}

dependencies {
    implementation(projects.feature.list)
}