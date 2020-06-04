object Versions {

    object Android {
        const val compileSdk = 28
        const val targetSdk = 28
        const val minSdk = 21
    }


    const val kotlin = "1.3.72"

    object Libs {
        object Android {
            const val kotlinStdLib = Versions.kotlin
            const val appCompat = "1.1.0"
            const val material = "1.0.0"
            const val constraintLayout = "1.1.3"
            const val recyclerView = "1.0.0"
            const val cicerone = "5.1.0"
            const val toothpick = "3.1.0"
            const val spek = "2.0.10"
            const val expekt = "0.5.0"
        }

        object MultiPlatform {
            const val kotlinStdLib = Versions.kotlin

            const val coroutines = "1.3.5"
            const val serialization = "0.20.0"

            const val napier = "1.2.0"
            const val klock = "1.10.3"
        }

    }

    object Plugins {
        const val kotlin = Versions.kotlin
        const val serialization = Versions.kotlin
        const val androidExtensions = Versions.kotlin
    }

}