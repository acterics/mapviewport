package com.oleglipskiy.mapviewport.sample.app

import com.oleglipskiy.mapviewport.sample.views.Div
import com.oleglipskiy.mapviewport.sample.views.MapProjectionOverlayView
import com.oleglipskiy.mapviewport.sample.views.MapView
import kotlin.browser.document

class MainApplication : Application() {

    override val stateKeys = listOf<String>()

    private lateinit var mapView: MapView
    private lateinit var projectionOverlayView: MapProjectionOverlayView

    override fun start(state: Map<String, Any>) {
        val body = document.body ?: throw RuntimeException("Body is null!")

        val mapHolder = Div(body, "mapHolder")
        mapView = MapView(mapHolder.element)
        projectionOverlayView = MapProjectionOverlayView(mapHolder.element)

        mapView.overlayView = projectionOverlayView

    }


    override fun dispose() = mapOf<String, Any>()
}