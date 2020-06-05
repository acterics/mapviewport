package com.oleglipskiy.mapviewport.sample

import com.oleglipskiy.mapviewport.MapCamera
import com.oleglipskiy.mapviewport.MapViewport

fun main() {
    val mapViewport = MapViewport(500.0, 500.0, MapCamera(
        longitude = 35.3,
        latitude = 34.5,
        zoom = 13.0,
        pitch = 10.0,
        bearing = 0.0
    ))
    console.log("Created mapviewport: $mapViewport")
    console.log("Hello world")
}