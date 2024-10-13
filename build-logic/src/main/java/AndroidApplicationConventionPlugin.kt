import com.android.build.api.dsl.ApplicationExtension
import com.minjin.musinsa.convention.MusinsaConfig
import com.minjin.musinsa.convention.Plugins
import com.minjin.musinsa.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.ANDROID_APPLICATION)
                apply(Plugins.KOTLIN_ANDROID)
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                defaultConfig.apply {
                    applicationId = MusinsaConfig.APPLICATION_ID
                    targetSdk = MusinsaConfig.TARGET_SDK
                    versionCode = MusinsaConfig.VERSION_CODE
                    versionName = MusinsaConfig.VERSION_NAME
                }
            }
        }
    }
}