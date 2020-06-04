package com.oleglipskiy.mapviewport

import com.oleglipskiy.mapviewport.gl.Scalar
import com.oleglipskiy.mapviewport.gl.Vector2

data class ExtrudedPolygon(
        val id: Int,
        val vertices: List<Vector2>,
        val altitude: Scalar,
        val payload: Any?
)