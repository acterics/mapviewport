package com.oleglipskiy.mapviewport

import com.oleglipskiy.mapviewport.gl.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan


class MapViewport(private val width: Scalar,
                  private val height: Scalar,
                  private val camera: MapCamera) {

    private val fov = defaultFov
    private val viewMatrix: Matrix4
    private val inverseViewMatrix: Matrix4
    private val projectionMatrix: Matrix4
    private val inverseProjectionMatrix: Matrix4
    private val viewProjectionMatrix: Matrix4
    private val viewportMatrix: Matrix4
    private val inverseViewportMatrix: Matrix4
    private val pixelMatrix: Matrix4
    private val inversePixelMatrix: Matrix4

    init {
        val point = MercatorProjection.project(camera.latitude, camera.longitude, camera.zoom)
        val halfFov = fov / 2
        val cameraToCenterDistance = 0.5 / tan(halfFov) * height
        val groundAngle = halfPi + camera.pitch.radians
        val topHalfSurfaceDistance = sin(halfFov) * cameraToCenterDistance / sin((pi - groundAngle - halfFov).clamp(0.01, pi - 0.01))
        val furthestDistance = cos(halfPi - camera.pitch.radians) * topHalfSurfaceDistance + cameraToCenterDistance
        val farZ = furthestDistance * 0.01
        val nearZ = height / 50

        viewMatrix = Matrix4.identity
                .scaled(Vector3(1.0, -1.0, 1.0))
                .translated(Vector3(0.0, 0.0, -cameraToCenterDistance))
                .rotatedX(camera.pitch.radians)
                .rotatedZ(-camera.bearing.radians)
                .translated(Vector3(-point.x, -point.y, 0.0))

        inverseViewMatrix = viewMatrix.inverse
        projectionMatrix = Matrix4.perspective(fov, width / height, nearZ, farZ)
        inverseProjectionMatrix = projectionMatrix.inverse
        viewProjectionMatrix = projectionMatrix * viewMatrix
        viewportMatrix = Matrix4.identity
                .scaled(Vector3(width / 2, -height / 2, 1.0))
                .translated(Vector3(1.0, -1.0, 0.0))
        inverseViewportMatrix = viewportMatrix.inverse
        pixelMatrix = viewportMatrix * projectionMatrix * viewMatrix
        inversePixelMatrix = pixelMatrix.inverse

    }

    fun project(latitude: Scalar, longitude: Scalar, altitude: Scalar = 0.0): Vector2 {
        val flatWorldPosition = MercatorProjection.project(latitude, longitude, camera.zoom)
        val worldPosition = Vector3(
                flatWorldPosition.x,
                flatWorldPosition.y,
                MercatorProjection.zFromAltitude(altitude, latitude) * MercatorProjection.worldSize(camera.zoom.zoomToScale)
        )
        val decartViewportPosition = Vector4(worldPosition, 1.0).toVector3()
        return decartViewportPosition.xy
    }

    fun projectBases(extrudedPolygon: ExtrudedPolygon): List<List<Vector2>> {
        val bottomPolygon = extrudedPolygon.vertices.map { point ->
            project(point.y, point.x)
        }
        val topPolygon = extrudedPolygon.vertices.map { point ->
            project(point.y, point.x, extrudedPolygon.altitude)
        }
        return listOf(topPolygon, bottomPolygon)
    }

    fun projectPlanes(extrudedPolygon: ExtrudedPolygon): List<List<Vector2>> {
        var firstPoint = extrudedPolygon.vertices.firstOrNull() ?: return listOf()
        val sidePolygons = extrudedPolygon.vertices.map { vertex ->
            val bottomLeft = project(firstPoint.y, firstPoint.x)
            val topLeft = project(firstPoint.y, firstPoint.x, extrudedPolygon.altitude)
            val topRight = project(vertex.y, vertex.x, extrudedPolygon.altitude)
            val bottomRight = project(vertex.y, vertex.x)
            firstPoint = vertex
            listOf(topLeft, topRight, bottomRight, bottomLeft)
        }
        return sidePolygons + projectBases(extrudedPolygon)
    }

    fun getIntersectedPolygon(polygons: List<ExtrudedPolygon>, point: Vector2): ExtrudedPolygon? {
        if (polygons.isEmpty()) {
            return null
        }
        val worldLineP1 = Vector3(inverseViewMatrix.m41, inverseViewMatrix.m42, inverseViewMatrix.m43)
        val worldLineP2 = (Vector4(point.x, point.y, 0.0, 1.0) * inversePixelMatrix).toVector3()
        val worldLineDirection = (worldLineP2 - worldLineP1).normalizedOrZero()
        val worldLine = Line(position = worldLineP1, direction = worldLineDirection)
        val glPolygons: List<Polygon> = polygons.flatMap { polygon ->
            var first = polygon.vertices.lastOrNull() ?: return@flatMap emptyList<Polygon>()
            val sidePolygons = polygon.vertices.map { vertex ->
                val bottomLeft = worldTransform(first.y, first.x)
                val topLeft = worldTransform(first.y, first.x, polygon.altitude)
                val topRight = worldTransform(vertex.y, vertex.x, polygon.altitude)
                val bottomRight = worldTransform(vertex.y, vertex.x)
                first = vertex
                Polygon(polygon.id, listOf(topLeft, topRight, bottomRight, bottomLeft))
            }
            val bottomPolygon = Polygon(polygon.id,
                    polygon.vertices.map { vertex ->
                        worldTransform(vertex.y, vertex.x)
                    })
            val topPolygon = Polygon(polygon.id,
                    polygon.vertices.map { vertex ->
                        worldTransform(vertex.y,
                                vertex.x,
                                polygon.altitude)
                    })
            sidePolygons + listOf(topPolygon, bottomPolygon)
        }

        val intersectedPolygons = glPolygons.mapNotNull { polygon ->
            val scalarHolder = ScalarHolder(0.0)
            val intersects = polygon.intersects(worldLine, scalarHolder)
            val distance = scalarHolder.scalar
            if (!intersects || distance < 0) {
                null
            } else {
                polygon to distance
            }

        }
                .sortedByDescending { it.second }
        val intersectedId = intersectedPolygons.firstOrNull()?.first?.id ?: return null
        return polygons.firstOrNull { it.id == intersectedId }
    }


    private fun worldTransform(latitude: Scalar, longitude: Scalar, altitude: Scalar = 0.0): Vector3 {
        val flatWorldPosition: Vector2 = MercatorProjection.project(latitude, longitude, camera.zoom)
        return Vector3(
                flatWorldPosition.x,
                flatWorldPosition.y,
                MercatorProjection.zFromAltitude(altitude, latitude) * MercatorProjection.worldSize(camera.zoom.zoomToScale)
        )
    }


    companion object {
        const val defaultFov: Scalar = 0.6435011087932844
    }
}