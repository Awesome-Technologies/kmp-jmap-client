import org.gradle.api.publish.maven.MavenPublication
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.dokka)
    `maven-publish`
}

kotlin {
    explicitApi()
    @OptIn(ExperimentalAbiValidation::class)
    abiValidation {
        enabled.set(true)
    }

    androidLibrary {
        namespace = "chat.amp.jmap.core"
        compileSdk = 36
        minSdk = 24
    }

    jvm()

    js(IR) {
        nodejs()
    }

    val isMacHost = System.getProperty("os.name") == "Mac OS X"
    if (isMacHost) {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}

kotlin.jvmToolchain(25)

tasks.named("check") {
    dependsOn("dokkaGenerateHtml")
    if (System.getProperty("os.name") == "Mac OS X") {
        dependsOn("checkLegacyAbi")
    }
}

tasks.register("test") {
    group = "verification"
    description = "Runs unit tests for configured Kotlin Multiplatform targets."

    dependsOn("jvmTest", "jsNodeTest")
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("kmp-jmap-client-jmap-core")
            description.set("Core JMAP protocol models and primitives for Kotlin Multiplatform.")
            url.set("https://github.com/Awesome-Technologies/kmp-jmap-client")
            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            scm {
                connection.set("scm:git:https://github.com/Awesome-Technologies/kmp-jmap-client.git")
                developerConnection.set("scm:git:ssh://git@github.com/Awesome-Technologies/kmp-jmap-client.git")
                url.set("https://github.com/Awesome-Technologies/kmp-jmap-client")
            }
        }
    }
}
