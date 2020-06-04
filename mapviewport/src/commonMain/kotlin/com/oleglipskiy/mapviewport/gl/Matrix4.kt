package com.oleglipskiy.mapviewport.gl

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

data class Matrix4(
        var m11: Scalar, var m12: Scalar, var m13: Scalar, var m14: Scalar,
        var m21: Scalar, var m22: Scalar, var m23: Scalar, var m24: Scalar,
        var m31: Scalar, var m32: Scalar, var m33: Scalar, var m34: Scalar,
        var m41: Scalar, var m42: Scalar, var m43: Scalar, var m44: Scalar
) {

    constructor(q: Quaternion) : this(
            1 - 2 * (q.y * q.y + q.z * q.z), 2 * (q.x * q.y + q.z * q.w), 2 * (q.x * q.z - q.y * q.w), 0.0,
            2 * (q.x * q.y - q.z * q.w), 1 - 2 * (q.x * q.x + q.z * q.z), 2 * (q.y * q.z + q.x * q.w), 0.0,
            2 * (q.x * q.z + q.y * q.w), 2 * (q.y * q.z - q.x * q.w), 1 - 2 * (q.x * q.x + q.y * q.y), 0.0,
            0.0, 0.0, 0.0, 1.0)

    val adjugate: Matrix4
        get() {
            val m = identity

            m.m11 = m22 * m33 * m44 - m22 * m34 * m43
            m.m11 += -m32 * m23 * m44 + m32 * m24 * m43
            m.m11 += m42 * m23 * m34 - m42 * m24 * m33

            m.m21 = -m21 * m33 * m44 + m21 * m34 * m43
            m.m21 += m31 * m23 * m44 - m31 * m24 * m43
            m.m21 += -m41 * m23 * m34 + m41 * m24 * m33

            m.m31 = m21 * m32 * m44 - m21 * m34 * m42
            m.m31 += -m31 * m22 * m44 + m31 * m24 * m42
            m.m31 += m41 * m22 * m34 - m41 * m24 * m32

            m.m41 = -m21 * m32 * m43 + m21 * m33 * m42
            m.m41 += m31 * m22 * m43 - m31 * m23 * m42
            m.m41 += -m41 * m22 * m33 + m41 * m23 * m32

            m.m12 = -m12 * m33 * m44 + m12 * m34 * m43
            m.m12 += m32 * m13 * m44 - m32 * m14 * m43
            m.m12 += -m42 * m13 * m34 + m42 * m14 * m33

            m.m22 = m11 * m33 * m44 - m11 * m34 * m43
            m.m22 += -m31 * m13 * m44 + m31 * m14 * m43
            m.m22 += m41 * m13 * m34 - m41 * m14 * m33

            m.m32 = -m11 * m32 * m44 + m11 * m34 * m42
            m.m32 += m31 * m12 * m44 - m31 * m14 * m42
            m.m32 += -m41 * m12 * m34 + m41 * m14 * m32

            m.m42 = m11 * m32 * m43 - m11 * m33 * m42
            m.m42 += -m31 * m12 * m43 + m31 * m13 * m42
            m.m42 += m41 * m12 * m33 - m41 * m13 * m32

            m.m13 = m12 * m23 * m44 - m12 * m24 * m43
            m.m13 += -m22 * m13 * m44 + m22 * m14 * m43
            m.m13 += m42 * m13 * m24 - m42 * m14 * m23

            m.m23 = -m11 * m23 * m44 + m11 * m24 * m43
            m.m23 += m21 * m13 * m44 - m21 * m14 * m43
            m.m23 += -m41 * m13 * m24 + m41 * m14 * m23

            m.m33 = m11 * m22 * m44 - m11 * m24 * m42
            m.m33 += -m21 * m12 * m44 + m21 * m14 * m42
            m.m33 += m41 * m12 * m24 - m41 * m14 * m22

            m.m43 = -m11 * m22 * m43 + m11 * m23 * m42
            m.m43 += m21 * m12 * m43 - m21 * m13 * m42
            m.m43 += -m41 * m12 * m23 + m41 * m13 * m22

            m.m14 = -m12 * m23 * m34 + m12 * m24 * m33
            m.m14 += m22 * m13 * m34 - m22 * m14 * m33
            m.m14 += -m32 * m13 * m24 + m32 * m14 * m23

            m.m24 = m11 * m23 * m34 - m11 * m24 * m33
            m.m24 += -m21 * m13 * m34 + m21 * m14 * m33
            m.m24 += m31 * m13 * m24 - m31 * m14 * m23

            m.m34 = -m11 * m22 * m34 + m11 * m24 * m32
            m.m34 += m21 * m12 * m34 - m21 * m14 * m32
            m.m34 += -m31 * m12 * m24 + m31 * m14 * m22

            m.m44 = m11 * m22 * m33 - m11 * m23 * m32
            m.m44 += -m21 * m12 * m33 + m21 * m13 * m32
            m.m44 += m31 * m12 * m23 - m31 * m13 * m22

            return m
        }

    val determinant: Scalar
        get() = determinant(adjugate)

    fun determinant(adjugate: Matrix4): Scalar {
        return m11 * adjugate.m11 + m12 * adjugate.m21 + m13 * adjugate.m31 + m14 * adjugate.m41
    }

    val transpose: Matrix4
        get() = Matrix4(
                m11, m21, m31, m41,
                m12, m22, m32, m42,
                m13, m23, m33, m43,
                m14, m24, m34, m44)

    val inverse: Matrix4
        get() {
            val adjugate = this.adjugate
            return adjugate * (1 / determinant(adjugate))
        }

    operator fun unaryMinus(): Matrix4 {
        return inverse
    }

    operator fun times(matrix: Matrix4): Matrix4 {
        val m = Matrix4.identity

        m.m11 = m11 * matrix.m11 + m21 * matrix.m12
        m.m11 += m31 * matrix.m13 + m41 * matrix.m14

        m.m12 = m12 * matrix.m11 + m22 * matrix.m12
        m.m12 += m32 * matrix.m13 + m42 * matrix.m14

        m.m13 = m13 * matrix.m11 + m23 * matrix.m12
        m.m13 += m33 * matrix.m13 + m43 * matrix.m14

        m.m14 = m14 * matrix.m11 + m24 * matrix.m12
        m.m14 += m34 * matrix.m13 + m44 * matrix.m14

        m.m21 = m11 * matrix.m21 + m21 * matrix.m22
        m.m21 += m31 * matrix.m23 + m41 * matrix.m24

        m.m22 = m12 * matrix.m21 + m22 * matrix.m22
        m.m22 += m32 * matrix.m23 + m42 * matrix.m24

        m.m23 = m13 * matrix.m21 + m23 * matrix.m22
        m.m23 += m33 * matrix.m23 + m43 * matrix.m24

        m.m24 = m14 * matrix.m21 + m24 * matrix.m22
        m.m24 += m34 * matrix.m23 + m44 * matrix.m24

        m.m31 = m11 * matrix.m31 + m21 * matrix.m32
        m.m31 += m31 * matrix.m33 + m41 * matrix.m34

        m.m32 = m12 * matrix.m31 + m22 * matrix.m32
        m.m32 += m32 * matrix.m33 + m42 * matrix.m34

        m.m33 = m13 * matrix.m31 + m23 * matrix.m32
        m.m33 += m33 * matrix.m33 + m43 * matrix.m34

        m.m34 = m14 * matrix.m31 + m24 * matrix.m32
        m.m34 += m34 * matrix.m33 + m44 * matrix.m34

        m.m41 = m11 * matrix.m41 + m21 * matrix.m42
        m.m41 += m31 * matrix.m43 + m41 * matrix.m44

        m.m42 = m12 * matrix.m41 + m22 * matrix.m42
        m.m42 += m32 * matrix.m43 + m42 * matrix.m44

        m.m43 = m13 * matrix.m41 + m23 * matrix.m42
        m.m43 += m33 * matrix.m43 + m43 * matrix.m44

        m.m44 = m14 * matrix.m41 + m24 * matrix.m42
        m.m44 += m34 * matrix.m43 + m44 * matrix.m44

        return m
    }

    operator fun times(vector: Vector3): Vector3 {
        return vector * this
    }

    operator fun times(vector: Vector4): Vector4 {
        return vector * this
    }

    operator fun times(scalar: Scalar): Matrix4 {
        return Matrix4(
                m11 * scalar, m12 * scalar, m13 * scalar, m14 * scalar,
                m21 * scalar, m22 * scalar, m23 * scalar, m24 * scalar,
                m31 * scalar, m32 * scalar, m33 * scalar, m34 * scalar,
                m41 * scalar, m42 * scalar, m43 * scalar, m44 * scalar)
    }

    infix fun closeTo(value: Matrix4): Boolean {
        if (!(m11 closeTo value.m11)) {
            return false
        }
        if (!(m12 closeTo value.m12)) {
            return false
        }
        if (!(m13 closeTo value.m13)) {
            return false
        }
        if (!(m14 closeTo value.m14)) {
            return false
        }
        if (!(m21 closeTo value.m21)) {
            return false
        }
        if (!(m22 closeTo value.m22)) {
            return false
        }
        if (!(m23 closeTo value.m23)) {
            return false
        }
        if (!(m24 closeTo value.m24)) {
            return false
        }
        if (!(m31 closeTo value.m31)) {
            return false
        }
        if (!(m32 closeTo value.m32)) {
            return false
        }
        if (!(m33 closeTo value.m33)) {
            return false
        }
        if (!(m34 closeTo value.m34)) {
            return false
        }
        if (!(m41 closeTo value.m41)) {
            return false
        }
        if (!(m42 closeTo value.m42)) {
            return false
        }
        if (!(m43 closeTo value.m43)) {
            return false
        }
        if (!(m44 closeTo value.m44)) {
            return false
        }
        return true
    }

    fun translated(vector: Vector3): Matrix4 {
        val x = vector.x
        val y = vector.y
        val z = vector.z
        return Matrix4(
                m11, m12, m13, m14,
                m21, m22, m23, m24,
                m31, m32, m33, m34,

                m11 * x + m21 * y + m31 * z + m41,
                m12 * x + m22 * y + m32 * z + m42,
                m13 * x + m23 * y + m33 * z + m43,
                m14 * x + m24 * y + m34 * z + m44)
    }

    fun rotatedX(radians: Scalar): Matrix4 {
        val s = sin(radians)
        val c = cos(radians)
        return Matrix4(
                m11, m12, m13, m14,

                m21 * c + m31 * s,
                m22 * c + m32 * s,
                m23 * c + m33 * s,
                m24 * c + m34 * s,

                m31 * c - m21 * s,
                m32 * c - m22 * s,
                m33 * c - m23 * s,
                m34 * c - m24 * s,

                m41, m42, m43, m44)
    }

    fun rotatedY(radians: Scalar): Matrix4 {
        val s = sin(radians)
        val c = cos(radians)
        return Matrix4(
                m11 * c - m31 * s,
                m12 * c - m32 * s,
                m13 * c - m33 * s,
                m14 * c - m34 * s,

                m21, m22, m23, m24,

                m31 * c + m11 * s,
                m32 * c + m12 * s,
                m33 * c + m13 * s,
                m34 * c + m14 * s,

                m41, m42, m43, m44)
    }

    fun rotatedZ(radians: Scalar): Matrix4 {
        val s = sin(radians)
        val c = cos(radians)
        return Matrix4(
                m11 * c + m21 * s,
                m12 * c + m22 * s,
                m13 * c + m23 * s,
                m14 * c + m24 * s,

                m21 * c - m11 * s,
                m22 * c - m12 * s,
                m23 * c - m13 * s,
                m24 * c - m14 * s,

                m31, m32, m33, m34,
                m41, m42, m43, m44)
    }

    fun scaled(vector: Vector3): Matrix4 {
        val x = vector.x
        val y = vector.y
        val z = vector.z
        return Matrix4(
                m11 * x, m12 * x, m13 * x, m14 * x,
                m21 * y, m22 * y, m23 * y, m24 * y,
                m31 * z, m32 * z, m33 * z, m34 * z,
                m41, m42, m43, m44)
    }


    companion object {
        val identity: Matrix4
            get() = Matrix4(
                    1.0, 0.0, 0.0, 0.0,
                    0.0, 1.0, 0.0, 0.0,
                    0.0, 0.0, 1.0, 0.0,
                    0.0, 0.0, 0.0, 1.0)

        fun scale(scale: Vector3): Matrix4 {
            return Matrix4(
                    scale.x, 0.0, 0.0, 0.0,
                    0.0, scale.y, 0.0, 0.0,
                    0.0, 0.0, scale.z, 0.0,
                    0.0, 0.0, 0.0, 1.0)
        }

        fun translation(translation: Vector3): Matrix4 {
            return Matrix4(
                    1.0, 0.0, 0.0, 0.0,
                    0.0, 1.0, 0.0, 0.0,
                    0.0, 0.0, 1.0, 0.0,
                    translation.x, translation.y, translation.z, 1.0)
        }

        fun rotation(axisAngle: Vector4): Matrix4 {
            return Matrix4(Quaternion(axisAngle))
        }

        fun perspective(fovy: Scalar, aspect: Scalar, near: Scalar, far: Scalar): Matrix4 {
            if (fovy > twoPi) {
                throw RuntimeException("fovy not radians")
            }

            val f = 1 / tan(fovy / 2)
            val nf = 1 / (near - far)

            return Matrix4(
                    f / aspect, 0.0, 0.0, 0.0,
                    0.0, f, 0.0, 0.0,
                    0.0, 0.0, (far + near) * nf, -1.0,
                    0.0, 0.0, (far + near) * nf, 0.0)
        }
    }
}
