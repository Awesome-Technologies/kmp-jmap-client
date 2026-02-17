import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.dokka) apply false
}

group = providers.gradleProperty("GROUP").orElse("chat.amp").get()
version = providers.gradleProperty("VERSION_NAME").orElse("0.1.0-SNAPSHOT").get()

val ktlintPluginId = libs.plugins.ktlint.get().pluginId
val detektPluginId = libs.plugins.detekt.get().pluginId

subprojects {
    group = rootProject.group
    version = rootProject.version

    apply(plugin = ktlintPluginId)
    apply(plugin = detektPluginId)

    extensions.configure<DetektExtension> {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "17"
        reports {
            html.required.set(true)
            sarif.required.set(true)
            md.required.set(true)
            txt.required.set(false)
            xml.required.set(true)
        }
    }
}
