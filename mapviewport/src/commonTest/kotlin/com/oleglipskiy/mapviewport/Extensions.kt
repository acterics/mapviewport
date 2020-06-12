package com.oleglipskiy.mapviewport

import kotlin.test.assertTrue

fun assertCloseTo(actual: Double, expected: Double, delta: Double = 0.0) {
    assertTrue(
        actual >= expected - delta && actual <= expected + delta,
        "$actual should be close to $expected ±$delta"
    )
}