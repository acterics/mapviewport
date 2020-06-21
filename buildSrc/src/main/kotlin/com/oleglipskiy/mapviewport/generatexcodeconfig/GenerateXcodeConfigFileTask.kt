package com.oleglipskiy.mapviewport.generatexcodeconfig

import com.oleglipskiy.mapviewport.podspecrenderer.PodspecRenderTask
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class GenerateXcodeConfigFileTask: DefaultTask() {

    @Input
    lateinit var options: Map<String, String>

    @InputFile
    lateinit var templatePath: String

    @OutputFile
    lateinit var xcodeConfigPath: String


    init {
        group = "xcode"
        description = "Render xcode config file for xcode project"
    }

    @TaskAction
    fun generateXcodeConfigFileTask() {
        print("Render from template $templatePath to $xcodeConfigPath")
        val templateFile = File(templatePath)
        val podSpecFile = File(xcodeConfigPath)

        podSpecFile.writeText(
            templateFile.readText().replaceAllPlaceholders(options)
                .replace("//", "/\$()/")
        )
    }

    private fun String.replaceAllPlaceholders(placeholders: Map<String, String>): String {
        return placeholders.toList().fold(this) { acc, pair ->
            acc.replace("\${${pair.first}}", pair.second)
        }
    }
}

fun Project.withGenerateXcodeConfigFileTask(init: GenerateXcodeConfigFileTask.() -> Unit) {
    val task = tasks.create("generateXcodeConfig", GenerateXcodeConfigFileTask::class.java) {
        init()
    }
}