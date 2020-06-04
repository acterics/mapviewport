package com.oleglipskiy.mapviewport.gl

import kotlin.math.*

typealias Scalar = Double

// Scalar extensions
const val pi: Scalar = PI
const val halfPi: Scalar = PI / 2
const val quarterPi: Scalar = PI / 4
const val twoPi: Scalar = PI * 2
const val degreesPerRadian: Scalar = 180 / PI
const val radiansPerDegree: Scalar = PI / 180
const val epsilon: Scalar = 0.0001

infix fun Scalar.closeTo(value: Scalar): Boolean {
    return abs(this - value) < epsilon
}

fun Scalar.clamp(minValue: Scalar, maxValue: Scalar): Scalar {
    return max(minValue, min(maxValue, this))
}

val Scalar.radians: Scalar
    get() = this * radiansPerDegree

val Scalar.degrees: Scalar
    get() = this * degreesPerRadian

val Scalar.zoomToScale: Scalar
    get() = 2.0.pow(this)