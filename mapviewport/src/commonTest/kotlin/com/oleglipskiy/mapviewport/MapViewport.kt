package com.oleglipskiy.mapviewport


class MapViewportTests {
    fun testProjection() {
        val viewport = MapViewport(
            width = 375.0,
            height = 812.0,
            camera = MapCamera(
                longitude = 30.510769762390847,
                latitude = 50.406390759358594,
                zoom = 17.941584986438464,
                pitch = 45.0,
                bearing = 18.147708514121256
            )
        )
        val expectedPointX = 14.00001176735605
        val expectedPointY = 11.333331648340868

        val pointLat = 50.40792974939521
        val pointLng = 30.510806842080342

        val projection = viewport.project(latitude = pointLat, longitude = pointLng)
        assertCloseTo(projection.x, expectedPointX)
        assertCloseTo(projection.y, expectedPointY)
    }

}