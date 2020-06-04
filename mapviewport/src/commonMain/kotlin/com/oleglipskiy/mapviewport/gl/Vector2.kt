package com.oleglipskiy.mapviewport.gl

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class Vector2(var x: Scalar, var y: Scalar) {

    val lengthSquared: Scalar
        get() = x * x + y * y

    val length: Scalar
        get() = sqrt(lengthSquared)

    val inverse: Vector2
        get() = -this


    operator fun unaryMinus(): Vector2 {
        return Vector2(-x, -y)
    }

    operator fun plus(vector: Vector2): Vector2 {
        return Vector2(x + vector.x, y + vector.y)
    }

    operator fun minus(vector: Vector2): Vector2 {
        return Vector2(x - vector.x, y - vector.y)
    }

    operator fun times(vector: Vector2): Vector2 {
        return Vector2(x * vector.x, y * vector.y)
    }

    operator fun times(scalar: Scalar): Vector2 {
        return Vector2(x * scalar, y * scalar)
    }

    operator fun times(matrix: Matrix3): Vector2 {
        return Vector2(
                x * matrix.m11 + y * matrix.m21 + matrix.m31,
                x * matrix.m12 + y * matrix.m22 + matrix.m32
        )
    }

    operator fun div(vector: Vector2): Vector2 {
        return Vector2(x / vector.x, y / vector.y)
    }

    operator fun div(scalar: Scalar): Vector2 {
        return Vector2(x / scalar, y / scalar)
    }

    infix fun closeTo(vector: Vector2): Boolean {
        return x closeTo vector.x && y closeTo vector.y
    }

    infix fun dot(vector: Vector2): Scalar {
        return x * vector.x + y * vector.y
    }

    infix fun cross(vector: Vector2): Scalar {
        return x * vector.y - y * vector.x
    }

    fun normalized(): Vector2? {
        val lengthSquared = this.lengthSquared
        if (lengthSquared closeTo 0.0) {
            return null
        }
        if (lengthSquared closeTo 1.0) {
            return this
        }
        return this / sqrt(lengthSquared)
    }

    fun normalizedOrZero(): Vector2 {
        return normalized() ?: zero
    }

    fun rotated(radians: Scalar): Vector2 {
        val cs = cos(radians)
        val sn = sin(radians)
        return Vector2(x * cs - y * sn, x * sn + y * cs)
    }

    fun rotated(radians: Scalar, pivot: Vector2): Vector2 {
        return (this - pivot).rotated(radians) + pivot
    }

    fun angle(vector: Vector2): Scalar {
        if (this == vector) {
            return 0.0
        }
        val t1 = normalizedOrZero()
        val t2 = vector.normalizedOrZero()
        val cross = t1 cross t2
        val dot = t1.dot(t2).clamp(-1.0, 1.0)
        return atan2(cross, dot)
    }

    fun interpolated(vector: Vector2, t: Scalar): Vector2 {
        return this + (vector - this) * t
    }

    companion object {
        val zero: Vector2
            get() = Vector2(0.0, 0.0)
        val x: Vector2
            get() = Vector2(1.0, 0.0)
        val y: Vector2
            get() = Vector2(0.0, 1.0)
    }
}