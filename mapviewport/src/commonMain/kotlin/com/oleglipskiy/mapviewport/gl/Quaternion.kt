package com.oleglipskiy.mapviewport.gl

import kotlin.math.*

data class Quaternion(var x: Scalar, var y: Scalar, var z: Scalar, var w: Scalar) {

    val lengthSquared: Scalar
        get() = x * x + y * y + z * z + w * w

    val length: Scalar
        get() = sqrt(lengthSquared)

    val inverse: Quaternion
        get() = -this

    var xyz: Vector3
        get() = Vector3(x, y, z)
        set(value) {
            x = value.x
            y = value.y
            z = value.z
        }

    val pitch: Scalar
        get() = asin((2 * (w * y - z * x)).clamp(-1.0, 1.0))

    val yaw: Scalar
        get() = atan2(2 * (w * z + x * y), 1 - 2 * (y * y + z * z))

    val roll: Scalar
        get() = atan2(2 * (w * x + y * z), 1 - 2 * (x * x + y * y))

    fun toAxisAngle(): Vector4 {
        val scale = xyz.length
        return if (scale closeTo 0.0 || scale closeTo twoPi) {
            Vector4.z
        } else {
            Vector4(x / scale, y / scale, z / scale, acos(w) * 2)
        }
    }

    infix fun dot(v: Quaternion): Scalar {
        return x * v.x + y * v.y + z * v.z + w * v.w
    }

    fun normalized(): Quaternion? {
        val lengthSquared = this.lengthSquared
        if (lengthSquared closeTo 0.0) {
            return null
        }
        if (lengthSquared closeTo 1.0) {
            return this
        }
        return this / sqrt(lengthSquared)
    }

    fun normalizedOrZero(): Quaternion {
        return normalized() ?: zero
    }

    fun interpolated(q: Quaternion, t: Scalar): Quaternion {
        val dot = (this dot q).clamp(-1.0, 1.0)
        if (dot closeTo 1.0) {
            return (this + (q - this) * t).normalizedOrZero()
        }

        val theta = acos(dot) * t
        val t1 = this * cos(theta)
        val t2 = (q - (this * dot)).normalizedOrZero() * sin(theta)
        return t1 + t2
    }

    operator fun unaryMinus(): Quaternion {
        return Quaternion(-x, -y, -z, -w)
    }

    operator fun plus(q: Quaternion): Quaternion {
        return Quaternion(x + q.x, y + q.y, z + q.z, w + q.w)
    }

    operator fun minus(q: Quaternion): Quaternion {
        return Quaternion(x - q.x, y - q.y, z - q.z, w - q.w)
    }

    operator fun times(q: Quaternion): Quaternion {
        return Quaternion(
                w * q.x + x * q.w + y * q.z - z * q.y,
                w * q.y + y * q.w + z * q.x - x * q.z,
                w * q.z + z * q.w + x * q.y - y * q.x,
                w * q.w - x * q.x - y * q.y - z * q.z)
    }

    operator fun times(v: Vector3): Vector3 {
        return v * this
    }

    operator fun times(scalar: Scalar): Quaternion {
        return Quaternion(x * scalar, y * scalar, z * scalar, w * scalar)
    }

    operator fun div(scalar: Scalar): Quaternion {
        return Quaternion(x / scalar, y / scalar, z / scalar, w / scalar)
    }

    infix fun closeTo(q: Quaternion): Boolean {
        return x closeTo  q.x && y closeTo  q.y && z closeTo  q.z && w closeTo  q.w
    }

    companion object {
        val zero: Quaternion
            get() = Quaternion(0.0, 0.0, 0.0, 0.0)
        val identity: Quaternion
            get() = Quaternion(0.0, 0.0, 0.0, 1.0)

        operator fun invoke(axisAngle: Vector4): Quaternion {
            val r = axisAngle.w * 0.5
            val scale = sin(r)
            val a = axisAngle.xyz * scale
            return Quaternion(a.x, a.y, a.z, cos(r))
        }

        operator fun invoke(pitch: Scalar, yaw: Scalar, roll: Scalar): Quaternion {
            val t0 = cos(yaw * 0.5)
            val t1 = sin(yaw * 0.5)
            val t2 = cos(roll * 0.5)
            val t3 = sin(roll * 0.5)
            val t4 = cos(pitch * 0.5)
            val t5 = sin(pitch * 0.5)
            return Quaternion(
                    t0 * t3 * t4 - t1 * t2 * t5,
                    t0 * t2 * t5 + t1 * t3 * t4,
                    t1 * t2 * t4 - t0 * t3 * t5,
                    t0 * t2 * t4 + t1 * t3 * t5)
        }
    }
}