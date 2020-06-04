package com.oleglipskiy.mapviewport.gl

import kotlin.math.cos
import kotlin.math.sin

data class Matrix3(
        var m11: Scalar, var m12: Scalar, var m13: Scalar,
        var m21: Scalar, var m22: Scalar, var m23: Scalar,
        var m31: Scalar, var m32: Scalar, var m33: Scalar
) {

    val adjugate: Matrix3
        get() = Matrix3(
                m22 * m33 - m23 * m32,
                m13 * m32 - m12 * m33,
                m12 * m23 - m13 * m22,
                m23 * m31 - m21 * m33,
                m11 * m33 - m13 * m31,
                m13 * m21 - m11 * m23,
                m21 * m32 - m22 * m31,
                m12 * m31 - m11 * m32,
                m11 * m22 - m12 * m21
        )

    val determinant: Scalar
        get() = (m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32) - (m13 * m22 * m31 + m11 * m23 * m32 + m12 * m21 * m33)


    val transpose: Matrix3
        get() = Matrix3(m11, m21, m31, m12, m22, m32, m13, m23, m33)

    val inverse: Matrix3
        get() = adjugate * (1 / determinant)

    fun interpolated(m: Matrix3, t: Scalar): Matrix3 {
        return Matrix3(
                m11 + (m.m11 - m11) * t,
                m12 + (m.m12 - m12) * t,
                m13 + (m.m13 - m13) * t,
                m21 + (m.m21 - m21) * t,
                m22 + (m.m22 - m22) * t,
                m23 + (m.m23 - m23) * t,
                m31 + (m.m31 - m31) * t,
                m32 + (m.m32 - m32) * t,
                m33 + (m.m33 - m33) * t
        )
    }

    operator fun unaryMinus(): Matrix3 {
        return inverse
    }

    operator fun times(matrix: Matrix3): Matrix3 {
        return Matrix3(
                m11 * matrix.m11 + m21 * matrix.m12 + m31 * matrix.m13,
                m12 * matrix.m11 + m22 * matrix.m12 + m32 * matrix.m13,
                m13 * matrix.m11 + m23 * matrix.m12 + m33 * matrix.m13,
                m11 * matrix.m21 + m21 * matrix.m22 + m31 * matrix.m23,
                m12 * matrix.m21 + m22 * matrix.m22 + m32 * matrix.m23,
                m13 * matrix.m21 + m23 * matrix.m22 + m33 * matrix.m23,
                m11 * matrix.m31 + m21 * matrix.m32 + m31 * matrix.m33,
                m12 * matrix.m31 + m22 * matrix.m32 + m32 * matrix.m33,
                m13 * matrix.m31 + m23 * matrix.m32 + m33 * matrix.m33
        )
    }

    operator fun times(vector: Vector2): Vector2 {
        return vector * this
    }

    operator fun times(vector: Vector3): Vector3 {
        return vector * this
    }

    operator fun times(scalar: Scalar): Matrix3 {
        return Matrix3(
                m11 * scalar, m12 * scalar, m13 * scalar,
                m21 * scalar, m22 * scalar, m23 * scalar,
                m31 * scalar, m32 * scalar, m33 * scalar
        )
    }

    infix fun closeTo(matrix: Matrix3): Boolean {
        if (!(m11 closeTo  matrix.m11)) { return false }
        if (!(m12 closeTo matrix.m12)) { return false }
        if (!(m13 closeTo matrix.m13)) { return false }
        if (!(m21 closeTo matrix.m21)) { return false }
        if (!(m22 closeTo matrix.m22)) { return false }
        if (!(m23 closeTo matrix.m23)) { return false }
        if (!(m31 closeTo matrix.m31)) { return false }
        if (!(m32 closeTo matrix.m32)) { return false }
        if (!(m33 closeTo matrix.m33)) { return false }
        return true
    }


    companion object {
        val identity: Matrix3
            get() = Matrix3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0)

        fun scale(scale: Vector2): Matrix3 {
            return Matrix3(
                    scale.x, 0.0, 0.0,
                    0.0, scale.y, 0.0,
                    0.0, 0.0, 1.0
            )
        }

        fun translation(translation: Vector2): Matrix3 {
            return Matrix3(
                    1.0, 0.0, 0.0,
                    0.0, 1.0, 0.0,
                    translation.x, translation.y, 1.0
            )
        }

        fun rotation(radians: Scalar): Matrix3 {
            val cs = cos(radians)
            val sn = sin(radians)
            return Matrix3(cs, sn, 0.0,
                    -sn, cs, 0.0,
                    0.0, 0.0, 1.0)
        }
    }
}