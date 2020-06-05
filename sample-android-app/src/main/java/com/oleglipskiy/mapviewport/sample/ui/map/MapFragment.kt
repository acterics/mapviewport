package com.oleglipskiy.mapviewport.sample.ui.map

import android.os.Bundle
import android.view.View
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.oleglipskiy.mapviewport.MapCamera
import com.oleglipskiy.mapviewport.MapViewport
import com.oleglipskiy.mapviewport.sample.R
import com.oleglipskiy.mapviewport.sample.ui.base.BaseFragment

class MapFragment: BaseFragment(R.layout.fragment_map) {

    lateinit var mapView: MapView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.mapView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.getMapAsync { map ->
            map.addOnMapClickListener { coordinates -> handleMapClick(coordinates, map)}
        }

    }

    private fun handleMapClick(coordinates: LatLng, map: MapboxMap): Boolean {
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


}