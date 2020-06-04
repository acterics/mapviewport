package com.oleglipskiy.mapviewport.gl

import kotlin.math.max
import kotlin.math.min

data class Polygon(val id: Int,
                   val points: List<Vector3>) {
    val basisU: Vector3
    val basisV: Vector3
    val normal: Vector3

    init {
        if (points.size >= 2) {
            basisU = (points[1] - points[0]).normalized() ?: Vector3.x
            basisV = (Plane.planeCCW(points).normal cross basisU).normalized() ?: Vector3.y
        } else {
            basisU = Vector3.x
            basisV = Vector3.y
        }
        normal = basisU cross basisV
    }

    fun contains(point: Vector3, polygonThickness: Scalar = 1e-5): Boolean {
        if (points.size < 3) {
            return false
        }
        if (points.any { it closeTo point }) {
            return true
        }

        val dot = normal dot (points[0] - point)

        if (dot * dot > polygonThickness) {
            return false
        }

        var numIntersections: Int = 0
        var vt = points.last() - point
        var p0 = Vector2(vt.dot(basisU), vt.dot(basisV))
        for (element in points) {
            vt = element - point
            val p1 = Vector2(vt.dot(basisU), vt.dot(basisV))
            if (p0.y * p1.y <= 0 && p0.y != p1.y) {
                if (min(p0.x, p1.x) >= 0) {
                    numIntersections += 1
                } else if (max(p0.x, p1.x) >= 0) {
                    val d = p1 - p0
                    if (!(d.y closeTo 0.0)) {
                        val t = -p0.y / d.y
                        val x = p0.x + t * d.x
                        if (t in 0.0..1.0 && x > 0) {
                            numIntersections += 1
                        }
                    }
                }
            }
            p0 = p1
        }
        return numIntersections % 2 == 1
    }

    fun intersects(line: Line): Boolean {
        val scalarHolder = ScalarHolder(0.0)
        return intersects(line, scalarHolder)
    }

    internal fun intersects(line: Line, scalarHolder: ScalarHolder): Boolean {
        val plane = Plane.planeCCW(points)
        if (!plane.intersects(line, scalarHolder)) {
            return false
        }
        return contains(line.getPoint(scalarHolder.scalar))
    }
}