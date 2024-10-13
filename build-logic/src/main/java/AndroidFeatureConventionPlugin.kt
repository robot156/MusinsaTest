import com.minjin.musinsa.convention.implementation
import com.minjin.musinsa.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

internal class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("musinsa.android.library")
                apply("musinsa.android.library.compose")
                apply("musinsa.android.hilt")
            }

            dependencies {
//                implementation(project(":core:domain"))
//                implementation(project(":core:designsystem"))
//                implementation(project(":core:model"))
//                implementation(project(":core:ui"))

                // AndroidX
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.activity.compose)
                implementation(libs.bundles.androidx.lifecycle)

                // AndroidX Navigation
                implementation(libs.androidx.navigation.compose)

                // AndroidX Compose material3
                implementation(libs.androidx.compose.material3)

                // AndroidX Hilt
                implementation(libs.androidx.hilt.common)
                implementation(libs.androidx.hilt.navigation.compose)

                // Kotlin
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutines)
                implementation(libs.kotlinx.collections.immutable)

                // ETC
                implementation(libs.timber)
            }
        }
    }
}