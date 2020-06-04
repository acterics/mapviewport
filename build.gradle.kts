plugins {
    id("com.gradle.build-scan") version ("2.1")
}

//buildscript {
//    dependencies {
//        classpath("")
//    }
//}

allprojects {
    repositories {
        google()
        jcenter()

        maven { url = uri("https://kotlin.bintray.com/kotlin") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
        maven { url = uri("https://kotlin.bintray.com/ktor") }
        maven { url = uri("https://dl.bintray.com/icerockdev/moko") }
        maven { url = uri("https://dl.bintray.com/aakira/maven") }
        maven { url = uri("https://dl.bintray.com/spekframework/spek-dev") }
    }


    // workaround for https://youtrack.jetbrains.com/issue/KT-27170
    configurations.create("compileClasspath")
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}

//tasks.register("clean", Delete::class).configure {
//    delete(rootProject.buildDir)
//}