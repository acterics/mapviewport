package com.oleglipskiy.mapviewport.sample.app

import com.oleglipskiy.mapviewport.BuildConfig
import com.oleglipskiy.mapviewport.MapCamera
import com.oleglipskiy.mapviewport.MapViewport
import com.oleglipskiy.mapviewport.sample.extensions.jsObject
import mapboxgl.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.collections.Map

class MainApplication : Application() {

    override val stateKeys = listOf<String>()

    private lateinit var map: mapboxgl.Map
    private lateinit var mapElement: HTMLElement

    override fun start(state: Map<String, Any>) {
        console.log("Hot reloaded1")
        mapboxgl.accessToken = BuildConfig.MAPBOX_ACCESS_TOKEN
        mapElement = document.getElementById("map") as HTMLElement
        console.log("Map: $mapElement")
        map = mapboxgl.Map(
            options = jsObject {
                container = mapElement
                style = BuildConfig.MAPBOX_STYLE_URL
                center = jsObject { lat = 50.45466; lng = 30.5238 }
                zoom  = 15
                logoPosition = "bottom-right"
                pitch = 60
            }
        )

        map.on("click", ::handleMapClick)
    }

    private fun handleMapClick(event: MapEvent) {
        val cameraCenter = map.getCenter()
        val width = mapElement.offsetWidth.toDouble()
        val height = mapElement.offsetHeight.toDouble()
        val mapViewport = MapViewport(
            width = width,
            height = height,
            camera = MapCamera(
                latitude = cameraCenter.lat.toDouble(),
                longitude = cameraCenter.lng.toDouble(),
                zoom = map.getZoom().toDouble(),
                pitch = map.getPitch().toDouble(),
                bearing = map.getBearing().toDouble()
            )
        )
        val renderedBuildings = map.queryRenderedFeatures(
            listOf(Point(0, 0), Point(width, height)),
            options = jsObject<`T$1`> {
                layers = listOf("building-extrusion")
            })

        console.log("Rendered buildings: $renderedBuildings")
    }

    override fun dispose() = mapOf<String, Any>()
}