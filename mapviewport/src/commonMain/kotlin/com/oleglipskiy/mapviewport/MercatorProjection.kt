package com.oleglipskiy.mapviewport
import com.oleglipskiy.mapviewport.gl.*
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.tan

internal object MercatorProjection {
    const val tileSize: Scalar = 512.0
    const val earthCircumference: Scalar = 40.03e6
    const val maxValidLatitude: Scalar = 85.051129

    fun circumferenceAtLatitude(latitude: Scalar): Scalar {
        return earthCircumference * cos(latitude.radians)
    }

    fun xFromLng(longitude: Scalar): Scalar {
        return (180 + longitude) / 360
    }

    fun yFromLat(latitude: Scalar): Scalar {
        return (180 - (180 / pi * ln(tan(quarterPi + latitude * pi / 360)))) / 360
    }

    fun zFromAltitude(altitude: Scalar, latitude: Scalar): Scalar {
        return altitude / circumferenceAtLatitude(latitude)
    }

    fun project(latitude: Scalar, longitude: Scalar, zoom: Scalar): Vector2 {
        val lat = latitude.clamp(-maxValidLatitude, maxValidLatitude)
        val worldSize = worldSize(zoom.zoomToScale)
        return Vector2(
                xFromLng(longitude) * worldSize,
                yFromLat(lat) * worldSize
        )
    }

    fun worldSize(scale: Scalar): Scalar {
        return tileSize * scale
    }

}