package com.oleglipskiy.mapviewport.gl

import kotlin.math.abs
import kotlin.math.sqrt

internal class ScalarHolder(var scalar: Scalar)

data class Plane(val normal: Vector3,
                 val d: Scalar) {
    constructor(point: Vector3, normal: Vector3) : this(normal, point dot normal)

    fun distance(point: Vector3): Scalar {
        return abs(signedDistance(point))
    }

    fun signedDistance(point: Vector3): Scalar {
        return (normal dot point) - d
    }

    fun project(point: Vector3): Vector3 {
        return point - (normal * ((normal dot point) - d))
    }

    internal fun intersects(line: Line, scalarHolder: ScalarHolder): Boolean {
        val denom = normal dot line.direction

        if (abs(denom) > 1e-4) {
            scalarHolder.scalar = (d - (normal dot line.position)) / denom
            return true
        }
        if (!(denom closeTo 0.0)) {
            scalarHolder.scalar = (d - (normal dot line.position)) / denom
            if (abs(scalarHolder.scalar) < 1e-4) {
                return true
            }
        }
        scalarHolder.scalar = 0.0
        return abs((normal dot line.position) - d) <= 1e-3
    }


    companion object {

        fun planeCCW(points: List<Vector3>): Plane {
            if (points.size > 3) {
                for (i in 0 until (points.size - 2)) {
                    for (j in (i + 1) until (points.size - 1)) {
                        val pij = points[j] - points[i]
                        for (k in (j + 1) until points.size) {
                            var normal = pij cross (points[k] - points[i])
                            val lengthSq = normal.lengthSquared
                            if (lengthSq > 1e-8) {
                                normal /= sqrt(lengthSq)
                                val d = normal.dot(points[i])
                                return Plane(normal, d)
                            }
                        }
                    }
                }
                val dir = (points[1] - points[0]).normalized() ?: Vector3.x
                return Plane(Line(points[0], dir), dir.perpendicular())
            }
            if (points.size == 3) {
                return Plane(points[0], points[1], points[2])
            }
            if (points.size == 2) {
                val dir = (points[1] - points[0]).normalized() ?: Vector3.x
                return Plane(Line(points[0], dir), dir.perpendicular())
            }
            if (points.size == 1) {
                return Plane(points[0], Vector3.y)
            }
            return Plane(Vector3.x, Vector3.y, Vector3.z)
        }

        operator fun invoke(line: Line, normal: Vector3): Plane {
            val orthoNormal = normal - normal.projectionToNormal(line.direction)
            val planeNormal = orthoNormal.normalized() ?: Vector3.x
            return Plane(planeNormal, line.position dot normal) // Maybe bug?
        }

        operator fun invoke(v1: Vector3, v2: Vector3, v3: Vector3): Plane {
            val normal = ((v2 - v1) cross (v3 - v1)).normalized() ?: Vector3.x
            return Plane(normal, normal dot v1)
        }


    }
}