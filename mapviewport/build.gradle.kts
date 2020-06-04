import de.mannodermaus.gradle.plugins.junit5.junitPlatform
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlinx-serialization")
    id("dev.icerock.mobile.multiplatform")
    id("de.mannodermaus.android-junit5")
}

kotlin {
    js() {
        browser()
        nodejs()
        compilations.all {
            tasks.withType(Kotlin2JsCompile::class.java) {
                kotlinOptions {
                    moduleKind = "umd"
                }
            }
        }
    }
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
    }

    testOptions {
        junitPlatform {
            filters {
                includeEngines("spek2")
            }
        }
    }
}


tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
    kotlinOptions.freeCompilerArgs += listOf("-Xuse-experimental=kotlin.Experimental")
}

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
}



setupFramework(
    name = "MapViewport",
    exports = listOf()
)