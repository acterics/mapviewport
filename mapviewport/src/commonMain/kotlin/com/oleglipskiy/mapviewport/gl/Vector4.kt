package com.oleglipskiy.mapviewport.gl

import kotlin.math.sqrt

data class Vector4(var x: Scalar, var y: Scalar, var z: Scalar, var w: Scalar) {

    constructor(vector: Vector3, w: Scalar): this(vector.x, vector.y, vector.z, w)

    val lengthSquared: Scalar
        get() = x * x + y * y + z * z + w * w

    val length: Scalar
        get() = sqrt(lengthSquared)

    val inverse: Vector4
        get() = -this

    var xyz: Vector3
        get() = Vector3(x, y, z)
        set(value) {
            x = value.x
            y = value.y
            z = value.z
        }

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


    operator fun unaryMinus(): Vector4 {
        return Vector4(-x, -y, -z, -w)
    }

    operator fun plus(vector: Vector4): Vector4 {
        return Vector4(x + vector.x, y + vector.y, z + vector.z, w + vector.w)
    }

    operator fun minus(vector: Vector4): Vector4 {
        return Vector4(x - vector.x, y - vector.y, z - vector.z, w - vector.w)
    }

    operator fun times(vector: Vector4): Vector4 {
        return Vector4(x * vector.x, y * vector.y, z * vector.z, w * vector.w)
    }

    operator fun times(scalar: Scalar): Vector4 {
        return Vector4(x * scalar, y * scalar, z * scalar, w * scalar)
    }

    operator fun times(matrix: Matrix4): Vector4 {
        return Vector4(
                x * matrix.m11 + y * matrix.m21 + z * matrix.m31 + w * matrix.m41,
                x * matrix.m12 + y * matrix.m22 + z * matrix.m32 + w * matrix.m42,
                x * matrix.m13 + y * matrix.m23 + z * matrix.m33 + w * matrix.m43,
                x * matrix.m14 + y * matrix.m24 + z * matrix.m34 + w * matrix.m44
        )
    }

    operator fun div(vector: Vector4): Vector4 {
        return Vector4(x / vector.x, y / vector.y, z / vector.z, w / vector.w)
    }

    operator fun div(scalar: Scalar): Vector4 {
        return Vector4(x / scalar, y / scalar, z / scalar, w / scalar)
    }

    infix fun closeTo(vector: Vector4): Boolean {
        return x closeTo vector.x && y closeTo vector.y && z closeTo vector.z && w closeTo vector.w
    }

    fun toVector3(): Vector3 {
        return if (w closeTo 0.0) {
            xyz
        } else {
            xyz / w
        }
    }

    infix fun dot(vector: Vector4): Scalar {
        return x * vector.x + y * vector.y + z * vector.z + w * vector.w
    }

    fun normalized(): Vector4? {
        val lengthSquared = this.lengthSquared
        if (lengthSquared closeTo 0.0) {
            return null
        }
        if (lengthSquared closeTo 1.0) {
            return this
        }
        return this / sqrt(lengthSquared)
    }

    fun normalizedOrZero(): Vector4 {
        return normalized() ?: Vector4.zero
    }

    fun interpolated(vector: Vector4, t: Scalar): Vector4 {
        return this + (vector - this) * t
    }

    companion object {
        val zero: Vector4
            get() = Vector4(0.0, 0.0, 0.0, 0.0)
        val x: Vector4
            get() = Vector4(1.0, 0.0, 0.0, 0.0)
        val y: Vector4
            get() = Vector4(0.0, 1.0, 0.0, 0.0)
        val z: Vector4
            get() = Vector4(0.0, 0.0, 1.0, 0.0)
        val w: Vector4
            get() = Vector4(0.0, 0.0, 0.0, 1.0)
    }
}