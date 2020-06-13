package com.oleglipskiy.mapviewport.sample.views

import com.oleglipskiy.mapviewport.gl.Vector2
import org.w3c.dom.*
import kotlin.dom.appendElement

typealias Polygon = List<Vector2>

class MapProjectionOverlayView(parent: HTMLElement) {

    val element: HTMLCanvasElement
    val name = "projectionOverlay"

    val polygons: MutableList<Polygon> = mutableListOf()
    val greenPoints: MutableList<Vector2> = mutableListOf()
    val yellowPoints: MutableList<Vector2> = mutableListOf()

    init {
        val div = Div(parent, "canvasHolder")
        div.element.appendElement("canvas") {
            this.className = name
        }
        element = div.element.getElementsByClassName(name)[0] as HTMLCanvasElement
        element.width = div.element.offsetWidth
        element.height = div.element.offsetHeight
    }

    fun clear() {
        polygons.clear()
        greenPoints.clear()
        yellowPoints.clear()
    }

    fun draw() {
        val context2D: CanvasRenderingContext2D = element.getContext("2d") as CanvasRenderingContext2D
        context2D.clearRect(0.0, 0.0, element.width.toDouble(), element.height.toDouble())
//        context2D.fillStyle = "#FF0000"
//
//        context2D.fillRect(0.0, 0.0, element.offsetWidth.toDouble(), element.offsetHeight.toDouble())

        context2D.fillStyle = "#0000FF"
        context2D.strokeStyle = "00ffff"
        polygons.forEach { polygon ->
            context2D.beginPath()
            val first = polygon.firstOrNull() ?: return@forEach
            context2D.moveTo(first.x, first.y)
            polygon.drop(1).forEach { point ->
                context2D.lineTo(point.x, point.y)
            }
            context2D.lineTo(first.x, first.y)
            context2D.fill()
            context2D.stroke()
        }

        yellowPoints.forEach { point ->
            context2D.fillStyle = "#ffff00"
            context2D.fillRect(point.x - 0.5, point.y - 0.5, 1.0, 1.0)
        }

        greenPoints.forEach { point ->
            context2D.fillStyle = "#00ff00"
            context2D.fillRect(point.x - 0.5, point.y - 0.5, 1.0, 1.0)

        }


    }

}