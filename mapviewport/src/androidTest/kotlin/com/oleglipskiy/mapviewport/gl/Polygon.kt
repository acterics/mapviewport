package com.oleglipskiy.mapviewport.gl

import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object PolylineDecoderTest : Spek({

    describe("test Polygon.contains") {
        val polygon = Polygon(
            id = 0, points = listOf(
                Vector3(0.0, 0.0, 0.0),
                Vector3(0.0, 5.0, 0.0),
                Vector3(5.0, 5.0, 0.0),
                Vector3(5.0, 0.0, 0.0)
            )
        )
        it("should includes polygon points") {
            polygon.contains(Vector3(5.0, 0.0, 0.0)).should.be.`true`
            polygon.contains(Vector3(0.0, 0.0, 0.0)).should.be.`true`
            polygon.contains(Vector3(0.0, 5.0, 0.0)).should.be.`true`
            polygon.contains(Vector3(5.0, 5.0, 0.0)).should.be.`true`
        }
        it("should includes points on edges") {
            polygon.contains(Vector3(5.0, 2.0, 0.0)).should.be.`true`
            polygon.contains(Vector3(2.0, 5.0, 0.0)).should.be.`true`
        }
        it("should includes points inside") {
            polygon.contains(Vector3(1.0, 1.0, 0.0)).should.be.`true`
            polygon.contains(Vector3(2.0, 2.0, 0.0)).should.be.`true`
            polygon.contains(Vector3(4.0, 2.0, 0.0)).should.be.`true`
            polygon.contains(Vector3(1.0, 3.0, 0.0)).should.be.`true`
            polygon.contains(Vector3(2.0, 3.0, 0.0)).should.be.`true`
            polygon.contains(Vector3(4.0, 4.5, 0.0)).should.be.`true`
        }

        it("should not include points outside") {
            polygon.contains(Vector3(0.0, 0.0, -1.0)).should.be.`false`
            polygon.contains(Vector3(-1.0, 0.0, 0.0)).should.be.`false`
            polygon.contains(Vector3(0.0, -1.0, 0.0)).should.be.`false`
            polygon.contains(Vector3(5.1, 0.0, 0.0)).should.be.`false`
            polygon.contains(Vector3(-0.1, 0.0, 0.0)).should.be.`false`
        }


    }
})