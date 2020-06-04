plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()

    jcenter()
    google()

    maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }
}

dependencies {
    implementation("dev.icerock:mobile-multiplatform:0.6.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.70")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:0.10.1")
    implementation("com.android.tools.build:gradle:3.5.3")
    implementation("de.mannodermaus.gradle.plugins:android-junit5:1.5.2.0")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}