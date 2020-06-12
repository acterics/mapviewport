package com.oleglipskiy.mapviewport.sample.ui.map

import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mapbox.geojson.Polygon
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.FillExtrusionLayer
import com.oleglipskiy.mapviewport.MapCamera
import com.oleglipskiy.mapviewport.MapViewport
import com.oleglipskiy.mapviewport.sample.BuildConfig
import com.oleglipskiy.mapviewport.sample.R
import com.oleglipskiy.mapviewport.sample.extensions.Mapbox
import com.oleglipskiy.mapviewport.sample.ui.base.BaseFragment

class MapFragment : BaseFragment(R.layout.fragment_map) {

    lateinit var mapView: MapView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.mapView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        mapView.getMapAsync { map ->
            map.setStyle(Style.Builder().fromUri(BuildConfig.MAPBOX_STYLE_URL))
            map.addOnMapClickListener { coordinates -> handleMapClick(coordinates, map) }
            map.cameraPosition = CameraPosition.Builder()
                .zoom(13.0)
                .target(Mapbox.KYIV_COORDINATES)
                .tilt(60.0)
                .build()
        }

    }

    private fun handleMapClick(coordinates: LatLng, map: MapboxMap): Boolean {
        Log.d("DEBUG", "handleMapClick")
        val renderedBuildings = map.queryRenderedFeatures(
            RectF(0f, 0f, map.width, map.height),
            Mapbox.BUILDINGS_LAYER
        )

        renderedBuildings.mapNotNull { feature ->
            val polygon = feature.geometry() as? Polygon ?: return@mapNotNull null

            null
        }


        val mapViewport = MapViewport(
            map.width.toDouble(),
            map.height.toDouble(),
            map.viewportCamera
        )

        return false
    }

    private val MapboxMap.viewportCamera: MapCamera
        get() {
            return MapCamera(
                cameraPosition.target.latitude,
                cameraPosition.target.longitude,
                cameraPosition.zoom,
                cameraPosition.tilt,
                cameraPosition.bearing
            )
        }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }


}