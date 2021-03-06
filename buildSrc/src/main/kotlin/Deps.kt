object Deps {
    object Plugins {
        const val kotlinSerialization =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.Plugins.serialization}"
        const val androidExtensions =
            "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.Plugins.androidExtensions}"
    }

    object Libs {
        object Android {
            val kotlinStdLib = AndroidLibrary(
                name = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Libs.Android.kotlinStdLib}"
            )
            val kotlinReflect = AndroidLibrary(
                name = "org.jetbrains.kotlin:kotlin-reflect:${Versions.Libs.Android.kotlinStdLib}"
            )

            val appCompat = AndroidLibrary(
                name = "androidx.appcompat:appcompat:${Versions.Libs.Android.appCompat}"
            )
            val material = AndroidLibrary(
                name = "com.google.android.material:material:${Versions.Libs.Android.material}"
            )
            val recyclerView = AndroidLibrary(
                name = "androidx.recyclerview:recyclerview:${Versions.Libs.Android.recyclerView}"
            )
            val constraintLayout = AndroidLibrary(
                name = "androidx.constraintlayout:constraintlayout:${Versions.Libs.Android.constraintLayout}"
            )
            val cicerone = AndroidLibrary(
                name = "ru.terrakok.cicerone:cicerone:${Versions.Libs.Android.cicerone}"
            )
            val toothpick = AndroidLibrary(
                name = "com.github.stephanenicolas.toothpick:ktp:${Versions.Libs.Android.toothpick}"
            )
            val toothpickKapt = AndroidLibrary(
                name = "com.github.stephanenicolas.toothpick:toothpick-compiler:${Versions.Libs.Android.toothpick}"
            )

            val expekt = AndroidLibrary(
                name = "com.winterbe:expekt:${Versions.Libs.Android.expekt}"
            )

            val mapbox = AndroidLibrary(
                name = "com.mapbox.mapboxsdk:mapbox-android-sdk:${Versions.Libs.Android.mapbox}"
            )


        }

        object MultiPlatform {
            val kotlinStdLib = MultiPlatformLibrary(
                android = Android.kotlinStdLib.name,
                common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.Libs.MultiPlatform.kotlinStdLib}"
            )

            val coroutines = MultiPlatformLibrary(
                android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libs.MultiPlatform.coroutines}",
                common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.Libs.MultiPlatform.coroutines}",
                ios = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.Libs.MultiPlatform.coroutines}"
            )
            val serialization = MultiPlatformLibrary(
                android = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.Libs.MultiPlatform.serialization}",
                common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.Libs.MultiPlatform.serialization}",
                ios = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.Libs.MultiPlatform.serialization}"
            )

            val napier = MultiPlatformLibrary(
                android = "com.github.aakira:napier-android:${Versions.Libs.MultiPlatform.napier}",
                common = "com.github.aakira:napier:${Versions.Libs.MultiPlatform.napier}",
                iosX64 = "com.github.aakira:napier-iosX64:${Versions.Libs.MultiPlatform.napier}",
                iosArm64 = "com.github.aakira:napier-iosArm64:${Versions.Libs.MultiPlatform.napier}"
            )

            val spekDsl = MultiPlatformLibrary(
                android = "org.spekframework.spek2:spek-dsl-jvm:${Versions.Libs.Android.spek}",
                common = "org.spekframework.spek2:spek-dsl-metadata:${Versions.Libs.Android.spek}"
            )
            val spekRunner = MultiPlatformLibrary(
                android = "org.spekframework.spek2:spek-runner-junit5:${Versions.Libs.Android.spek}"
            )

            val kotlinTest = MultiPlatformLibrary(
                android = "org.jetbrains.kotlin:kotlin-test",
                ios = "org.jetbrains.kotlin:kotlin-test",
                common = "org.jetbrains.kotlin:kotlin-test"
            )
        }
        object JS {
            val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-js:${Versions.Libs.MultiPlatform.kotlinStdLib}"
            val kotlinTest = "org.jetbrains.kotlin:kotlin-test"
            val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.Libs.MultiPlatform.coroutines}"
        }

    }

    val plugins: Map<String, String> = mapOf(
        "kotlin-android-extensions" to Plugins.androidExtensions,
        "kotlinx-serialization" to Plugins.kotlinSerialization
    )
}