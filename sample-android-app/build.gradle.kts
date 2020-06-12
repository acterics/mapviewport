plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")

}

//dotenv {
//    println("Project root: $rootDir")
//    dir = "$rootDir/sample-android-app"
//}



android {
    compileSdkVersion(Versions.Android.compileSdk)
    dexOptions {
        javaMaxHeapSize = "2g"
    }

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)

        applicationId = "com.oleglipskiy.mapviewport.sample"

        versionCode = 1
        versionName = "0.1.0"

        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "MAPBOX_ACCESS_TOKEN", "\"${env.MAPBOX_ACCESS_TOKEN.value}\"")
        buildConfigField("String", "MAPBOX_STYLE_URL", "\"${env.MAPBOX_STYLE_URL.value}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}



androidExtensions {
    isExperimental = true
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Deps.Libs.Android.kotlinStdLib.name)

    implementation(Deps.Libs.Android.appCompat.name)
    implementation(Deps.Libs.Android.material.name)
    implementation(Deps.Libs.Android.recyclerView.name)
    implementation(Deps.Libs.Android.constraintLayout.name)
    implementation(Deps.Libs.Android.cicerone.name)


    implementation(Deps.Libs.MultiPlatform.coroutines.android!!)
    implementation(Deps.Libs.MultiPlatform.napier.android!!)

    implementation(Deps.Libs.Android.toothpick.name)
    kapt(Deps.Libs.Android.toothpickKapt.name)

    implementation(Deps.Libs.Android.mapbox.name)

    implementation(project(Modules.MultiPlatform.mapviewport.name))
}
