import com.minjin.musinsa.convention.Plugins
import com.minjin.musinsa.convention.implementation
import com.minjin.musinsa.convention.ksp
import com.minjin.musinsa.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class JvmHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.KSP)
            }

            dependencies {
                implementation(libs.hilt.core)
                ksp(libs.hilt.compiler)
            }
        }
    }
}