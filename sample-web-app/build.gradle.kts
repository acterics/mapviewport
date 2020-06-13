import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackOutput.Target.COMMONJS
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("js")
    id("com.github.gmazzo.buildconfig") version "2.0.1"
}

// Workaround https://youtrack.jetbrains.com/issue/KT-34389
org.jetbrains.kotlin.gradle.targets.js.npm.NpmResolverPlugin.apply(project)
//apply(plugin = "kotlin2js")

group = "com.oleglipskiy.mapviewport.sample"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

buildConfig {
    useKotlinOutput {
        topLevelConstants = false
    }
    packageName = "com.oleglipskiy.mapviewport"
    buildConfigField("String", "MAPBOX_ACCESS_TOKEN", "\"${env.MAPBOX_ACCESS_TOKEN.value}\"")
    buildConfigField("String", "MAPBOX_STYLE_URL", "\"${env.MAPBOX_STYLE_URL.value}\"")


}


dependencies {
    implementation(Deps.Libs.JS.kotlinStdLib)
    implementation(project(Modules.MultiPlatform.mapviewport.name))
    implementation(npm("mapbox-gl"))
    implementation(npm("@types/mapbox-gl"))
}

kotlin.target {
    compilations["main"].apply {

        defaultSourceSet {
            // Includes generated directory to compile classpath
            val generatedPath = "$buildDir/generated/source/buildConfig/main/main"
            kotlin.srcDir(generatedPath)
        }
    }
    browser {
        webpackTask {
            output.libraryTarget = "umd"
            outputFileName = "main.js"
        }
        runTask {
            devServer = KotlinWebpackConfig.DevServer(
                true, false, true, true, false,
                8080,
                null,
                listOf("${projectDir}/src/main/resources")
            )
            outputFileName = "main.js"
        }
//    // execute :js:browserWebpack to build webpack bundle in `./build/distributions`

    }
}

afterEvaluate {
    tasks.findByPath("compileKotlinJs")!!.dependsOn("generateBuildConfig")
}