import com.android.build.gradle.LibraryExtension
import com.minjin.musinsa.convention.MusinsaConfig
import com.minjin.musinsa.convention.Plugins
import com.minjin.musinsa.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.ANDROID_LIBRARY)
                apply(Plugins.KOTLIN_ANDROID)
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = MusinsaConfig.TARGET_SDK
            }
        }
    }
}