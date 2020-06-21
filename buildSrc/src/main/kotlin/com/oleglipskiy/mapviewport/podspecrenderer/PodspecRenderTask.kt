package com.oleglipskiy.mapviewport.podspecrenderer

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class PodspecRenderTask: DefaultTask() {

    @Input
    lateinit var options: Map<String, String>

    @InputFile
    lateinit var templatePath: String

    @OutputFile
    lateinit var podspecPath: String


    init {
        group = "cocoapods"
        description = "Render cocoapods .podspec file from template"
    }

    @TaskAction
    fun renderPodspecFile() {
        print("Render from template $templatePath to $podspecPath")
        val templateFile = File(templatePath)
        val podSpecFile = File(podspecPath)

        podSpecFile.writeText(
            templateFile.readText().replaceAllPlaceholders(options)
        )
    }

    private fun String.replaceAllPlaceholders(placeholders: Map<String, String>): String {
        return placeholders.toList().fold(this) { acc, pair ->
            acc.replace("\${${pair.first}}", pair.second)
        }
    }

}

fun Project.withRenderPodspecTask(init: PodspecRenderTask.() -> Unit) {
    val task = tasks.create("renderPodspec", PodspecRenderTask::class.java) {
        init()
    }
}