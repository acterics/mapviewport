package com.oleglipskiy.mapviewport.gl

data class Line(val position: Vector3,
                val direction: Vector3) {
    fun getPoint(d: Scalar): Vector3 {
        return position + (direction * d)
    }
}