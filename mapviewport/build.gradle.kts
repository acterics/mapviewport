import de.mannodermaus.gradle.plugins.junit5.junitPlatform
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackOutput.Target.COMMONJS
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("dev.icerock.mobile.multiplatform")
    id("de.mannodermaus.android-junit5")
}

kotlin {
    js() {
        browser {
            webpackTask {
                output.libraryTarget = COMMONJS
            }
        }
        nodejs()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(Deps.Libs.JS.kotlinStdLib)

            }
        }
        val jsTest by getting {
            dependencies {
                implementation(Deps.Libs.JS.kotlinTest)
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

    "commonTestImplementation"(Deps.Libs.MultiPlatform.kotlinTest.common!!)

    "androidTestImplementation"(Deps.Libs.MultiPlatform.spekDsl.android!!)
    "androidTestImplementation"(Deps.Libs.MultiPlatform.spekRunner.android!!)
    "androidTestImplementation"(Deps.Libs.Android.kotlinReflect.name)
    "androidTestImplementation"(Deps.Libs.Android.expekt.name)

    "iosArm64TestImplementation"(Deps.Libs.MultiPlatform.kotlinTest.iosArm64!!)
    "iosX64TestImplementation"(Deps.Libs.MultiPlatform.kotlinTest.iosX64!!)
}



setupFramework(
    name = "MapViewport",
    exports = listOf()
)