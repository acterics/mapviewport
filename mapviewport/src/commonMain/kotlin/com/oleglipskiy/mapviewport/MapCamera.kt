package com.oleglipskiy.mapviewport

import com.oleglipskiy.mapviewport.gl.Scalar

data class MapCamera(val longitude: Scalar,
                     val latitude: Scalar,
                     val zoom: Scalar,
                     val pitch: Scalar,
                     val bearing: Scalar)