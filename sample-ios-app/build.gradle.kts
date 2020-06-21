import com.oleglipskiy.mapviewport.generatexcodeconfig.withGenerateXcodeConfigFileTask

withGenerateXcodeConfigFileTask {
    templatePath = "${projectDir}/gradle.xcconfig.template"
    xcodeConfigPath = "${buildDir}/gradle.xcconfig"
    options = mapOf(
        "MAPBOX_STYLE_URL" to env.MAPBOX_STYLE_URL.value,
        "MAPBOX_ACCESS_TOKEN" to env.MAPBOX_ACCESS_TOKEN.value
    )
}