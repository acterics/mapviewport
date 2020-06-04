package com.oleglipskiy.mapviewport.gl

import kotlin.math.sqrt

data class Vector3(var x: Scalar, var y: Scalar, var z: Scalar) {
    val lengthSquared: Scalar
        get() = x * x + y * y + z * z

    val length: Scalar
        get() = sqrt(lengthSquared)

    val inverse: Vector3
        get() = -this

    var xy: Vector2
        get() = Vector2(x, y)
        set(value) {
            x = value.x
            y = value.y
        }

    var xz: Vector2
        get() = Vector2(x, z)
        set(value) {
            x = value.x
            z = value.y
        }

    var yz: Vector2
        get() = Vector2(y, z)
        set(value) {
            y = value.x
            z = value.y
        }


    operator fun unaryMinus(): Vector3 {
        return Vector3(-x, -y, -z)
    }

    operator fun plus(vector: Vector3): Vector3 {
        return Vector3(x + vector.x, y + vector.y, z + vector.z)
    }

    operator fun minus(vector: Vector3): Vector3 {
        return Vector3(x - vector.x, y - vector.y, z - vector.z)
    }

    operator fun times(vector: Vector3): Vector3 {
        return Vector3(x * vector.x, y * vector.y, z * vector.z)
    }

    operator fun times(scalar: Scalar): Vector3 {
        return Vector3(x * scalar, y * scalar, z * scalar)
    }

    operator fun times(matrix: Matrix3): Vector3 {
        return Vector3(x * matrix.m11 + y * matrix.m21 + z * matrix.m31,
                x * matrix.m12 + y * matrix.m22 + z * matrix.m32,
                x * matrix.m13 + y * matrix.m23 + z * matrix.m33)
    }

    operator fun times(matrix: Matrix4): Vector3 {
        return Vector3(x * matrix.m11 + y * matrix.m21 + z * matrix.m31 + matrix.m41,
                x * matrix.m12 + y * matrix.m22 + z * matrix.m32 + matrix.m42,
                x * matrix.m13 + y * matrix.m23 + z * matrix.m33 + matrix.m43)
    }

    operator fun times(quaternion: Quaternion): Vector3 {
        TODO()
    }

    operator fun div(vector: Vector3): Vector3 {
        return Vector3(x / vector.x, y / vector.y, z / vector.z)
    }

    operator fun div(scalar: Scalar): Vector3 {
        return Vector3(x / scalar, y / scalar, z / scalar)
    }

    infix fun closeTo(vector: Vector3): Boolean {
        return x closeTo vector.x && y closeTo vector.y && z closeTo vector.z
    }

    infix fun dot(vector: Vector3): Scalar {
        return x * vector.x + y * vector.y + z * vector.z
    }

    infix fun cross(vector: Vector3): Vector3 {
        return Vector3(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x)
    }

    fun normalized(): Vector3? {
        val lengthSquared = this.lengthSquared
        if (lengthSquared closeTo 0.0) {
            return null
        }
        if (lengthSquared closeTo 1.0) {
            return this
        }
        return this / sqrt(lengthSquared)
    }

    fun normalizedOrZero(): Vector3 {
        return normalized() ?: zero
    }

    // direction must be normalized
    fun projectionToNormal(direction: Vector3): Vector3 {
        return direction * dot(direction)
    }

    fun perpendicular(hint1: Vector3 = Vector3.y, hint2: Vector3 = Vector3.z): Vector3 {
        val v = this cross hint1
        return v.normalized() ?: hint2
    }

    fun interpolated(v: Vector3, t: Scalar): Vector3 {
        return this + (v - this) * t
    }

    fun distanceSquared(point: Vector3): Scalar {
        val dx = x - point.x
        val dy = y - point.y
        val dz = z - point.z
        return dx * dx + dy * dy + dz * dz
    }

    fun distance(point: Vector3): Scalar {
        return sqrt(distanceSquared(point))
    }

    companion object {
        val zero: Vector3
            get() = Vector3(0.0, 0.0, 0.0)
        val x: Vector3
            get() = Vector3(1.0, 0.0, 0.0)
        val y: Vector3
            get() = Vector3(0.0, 1.0, 0.0)
        val z: Vector3
            get() = Vector3(0.0, 0.0, 1.0)
    }
}