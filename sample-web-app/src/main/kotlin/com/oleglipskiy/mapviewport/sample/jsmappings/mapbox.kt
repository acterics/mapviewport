package mapboxgl

import com.oleglipskiy.mapviewport.gl.Vector2
import com.oleglipskiy.mapviewport.sample.extensions.jsObject
import mapboxgl.LngLat

external interface MapEvent {
    val lngLat: LngLat
    val point: Vector2
    val type: String
}


external interface BBox {
    val xMin: Number
    val yMin: Number
    val xMax: Number
    val yMax: Number
}


class BoundingBox(
    override val xMin: Number,
    override val yMin: Number,
    override val xMax: Number,
    override val yMax: Number
) : BBox


