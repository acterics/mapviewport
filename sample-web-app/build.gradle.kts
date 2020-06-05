import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    id("org.jetbrains.kotlin.js")
}

group = "com.oleglipskiy.mapviewport.sample"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(Deps.Libs.JS.kotlinStdLib)
    implementation(project(Modules.MultiPlatform.mapviewport.name))
}

kotlin.target.browser {
    runTask {
        devServer = KotlinWebpackConfig.DevServer(
                true, false, true, true, false,
                8080,
                null,
                listOf("${projectDir}/src/main/resources")
        )
        outputFileName = "main.js"
    }
    // execute :js:browserWebpack to build webpack bundle in `./build/distributions`
    webpackTask {
        outputFileName = "main.js"
    }
}