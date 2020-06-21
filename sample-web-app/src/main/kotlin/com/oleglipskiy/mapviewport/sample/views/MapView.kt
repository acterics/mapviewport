package com.oleglipskiy.mapviewport.sample.views

import com.oleglipskiy.mapviewport.BuildConfig
import com.oleglipskiy.mapviewport.ExtrudedPolygon
import com.oleglipskiy.mapviewport.MapCamera
import com.oleglipskiy.mapviewport.MapViewport
import com.oleglipskiy.mapviewport.gl.Vector2
import com.oleglipskiy.mapviewport.sample.extensions.append
import com.oleglipskiy.mapviewport.sample.extensions.jsObject
import mapboxgl.MapEvent
import mapboxgl.Point
import mapboxgl.`T$1`
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.dom.appendElement

class MapView(
    parent: HTMLElement,
    mapOptions: dynamic = defaultMapOptions
) {
    private val buildingsLayer = "building-extrusion"

    val map: mapboxgl.Map
    val element: HTMLElement

    var overlayView: MapProjectionOverlayView? = null

    init {
        mapboxgl.accessToken = BuildConfig.MAPBOX_ACCESS_TOKEN
        parent.appendElement("div") {
            className = "map"
            id = "map"
        }
        element = document.getElementById("map") as HTMLElement
        map = mapboxgl.Map(
            options = append(mapOptions) {
                container = element
            }
        )

        map.on("click", ::handleMapClick)
    }

    private fun handleMapClick(event: MapEvent) {
        console.log("Clicked)")
        console.log("on view (${event.point.x}, ${event.point.y}")
        console.log("on map (${event.lngLat.lat}, ${event.lngLat.lng})")

        val cameraCenter = map.getCenter()
        val width = element.offsetWidth.toDouble()
        val height = element.offsetHeight.toDouble()
        val point = event.point
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
            arrayOf(Point(0, 0), Point(width, height)),
            options = jsObject<`T$1`> {
                layers = arrayOf(buildingsLayer)
            })

        val extrudedPolygons = renderedBuildings.mapIndexedNotNull { index, feature ->
            val vertices = (feature.geometry.coordinates[0] as? Array<Array<Double>>)
                ?: return@mapIndexedNotNull null
            val polygonHeight = feature.properties.height as? Double ?: return@mapIndexedNotNull null
            ExtrudedPolygon(
                id = index,
                vertices = vertices.map { pointArray -> Vector2(pointArray[0], pointArray[1]) },
                altitude = polygonHeight,
                payload = null
            )
        }

        val clickedPolygon = mapViewport.getIntersectedPolygon(extrudedPolygons, point)
        println("Clicked polygon: $clickedPolygon")
        val overlayView = this.overlayView
        if (overlayView != null) {
            overlayView.clear()
            if (clickedPolygon != null) {
                val polygons = mapViewport.projectPlanes(clickedPolygon)
                overlayView.polygons.addAll(polygons)
            }
            overlayView.yellowPoints.add(point)
            overlayView.draw()
        }

    }

    companion object {
        val defaultMapOptions = jsObject {
            style = BuildConfig.MAPBOX_STYLE_URL
            center = jsObject { lat = 50.45466; lng = 30.5238 }
            zoom = 15
            logoPosition = "bottom-right"
            pitch = 60
        }
    }

}