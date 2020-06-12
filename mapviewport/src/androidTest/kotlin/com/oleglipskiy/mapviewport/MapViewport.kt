package com.oleglipskiy.mapviewport

import com.oleglipskiy.mapviewport.gl.Matrix4
import com.oleglipskiy.mapviewport.gl.Scalar
import com.oleglipskiy.mapviewport.gl.closeTo
import com.oleglipskiy.mapviewport.gl.epsilon
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object MapViewportTests : Spek({

    describe("Test inverse matrix") {
        val width: Scalar = 375.0
        val height: Scalar = 554.0
        val longitude: Scalar = 44.744459388989249
        val latitude: Scalar = 41.712832248190693
        val zoom: Scalar = 16.349734211734528
        val pitch: Scalar = 10.0
        val bearing: Scalar = 180.0
        val viewport = MapViewport(
            width = width,
            height = height,
            camera = MapCamera(
                longitude = longitude,
                latitude = latitude,
                zoom = zoom,
                pitch = pitch,
                bearing = bearing
            )
        )

        it("inverse matrices should have product identity") {
            (viewport.viewMatrix * viewport.inverseViewMatrix).closeTo(Matrix4.identity).should.be.`true`
            (viewport.projectionMatrix * viewport.inverseProjectionMatrix).closeTo(Matrix4.identity).should.be.`true`
            (viewport.viewportMatrix * viewport.inverseViewportMatrix).closeTo(Matrix4.identity).should.be.`true`
            (viewport.pixelMatrix * viewport.inversePixelMatrix).closeTo(Matrix4.identity).should.be.`true`
        }

        it("matrices should have product identity") {
            (viewport.pixelMatrix * viewport.inverseViewMatrix).closeTo(Matrix4.identity).should.be.`false`
            (viewport.pixelMatrix * viewport.inverseProjectionMatrix).closeTo(Matrix4.identity).should.be.`false`
            (viewport.pixelMatrix * viewport.inverseViewportMatrix).closeTo(Matrix4.identity).should.be.`false`
        }
    }

    describe("Test point projection") {
        val viewport = MapViewport(
            width = 375.0,
            height = 812.0,
            camera = MapCamera(
                longitude = 30.510769762390847,
                latitude = 50.406390759358594,
                zoom = 17.941584986438464,
                pitch = 45.0,
                bearing = 18.147708514121256
            )
        )
        val expectedPointX = 14.00001176735605
        val expectedPointY = 11.333331648340868

        val pointLat = 50.40792974939521
        val pointLng = 30.510806842080342

        val projection = viewport.project(latitude = pointLat, longitude = pointLng)

        describe("should has expected projection") {
            it("should has expected x") {
                projection.x.should.closeTo(expectedPointX, epsilon)
            }
            it("should has expected y") {
                projection.y.should.closeTo(expectedPointY, epsilon)
            }

        }
    }

})