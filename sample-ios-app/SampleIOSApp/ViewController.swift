//
//  ViewController.swift
//  SampleIOSApp
//
//  Created by Oleg Lipskiy on 21.06.2020.
//  Copyright © 2020 Oleg Lipskiy. All rights reserved.
//

import Mapbox
import MapViewport
import UIKit

class ViewController: UIViewController {
    private var mapView: MGLMapView!
    private var debugOverlayView: MapProjectionOverlayView!

    private let tapGestureRecognizer = UITapGestureRecognizer()

    private let kyivCoordinates = CLLocationCoordinate2D(latitude: 50.45466,
                                                         longitude: 30.5238)

    private let buildingsLayer = "building-extrusion"

    override func viewDidLoad() {
        super.viewDidLoad()

        let styleUrl = URL(string: Bundle.main.infoDictionary?["MapboxStyleURL"] as! String)!

        mapView = MGLMapView()
        view.addSubview(mapView)

        debugOverlayView = MapProjectionOverlayView()
        view.addSubview(debugOverlayView)

        mapView.styleURL = styleUrl

        mapView.translatesAutoresizingMaskIntoConstraints = false
        mapView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        mapView.bottomAnchor.constraint(equalTo: debugOverlayView.topAnchor).isActive = true
        mapView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        mapView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        mapView.heightAnchor.constraint(equalTo: view.heightAnchor, multiplier: 0.5).isActive = true
        mapView.delegate = self

        tapGestureRecognizer.addTarget(self, action: #selector(handleMapTap(gesture:)))
        mapView.addGestureRecognizer(tapGestureRecognizer)

        debugOverlayView.translatesAutoresizingMaskIntoConstraints = false
        debugOverlayView.topAnchor.constraint(equalTo: mapView.bottomAnchor).isActive = true
        debugOverlayView.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        debugOverlayView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        debugOverlayView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        debugOverlayView.backgroundColor = .blue
    }

    @objc private func handleMapTap(gesture: UITapGestureRecognizer) {
        let cameraCenter = mapView.camera.centerCoordinate
        let width = mapView.frame.width
        let height = mapView.frame.height
        let point = gesture.location(in: mapView)

        let mapViewport = MapViewport(width: Double(width),
                                      height: Double(height),
                                      camera: MapCamera(longitude: cameraCenter.longitude,
                                                        latitude: cameraCenter.latitude,
                                                        zoom: mapView.zoomLevel,
                                                        pitch: Double(mapView.camera.pitch),
                                                        bearing: mapView.camera.heading))
        let visibleFeatures = mapView.visibleFeatures(in: mapView.frame,
                                                      styleLayerIdentifiers: [buildingsLayer])
        let extrudedPolygons = visibleFeatures.enumerated().compactMap { index, feature -> ExtrudedPolygon? in
            guard let polygonFearure = feature as? MGLPolygonFeature else { return nil }
            let vertices = Array(UnsafeBufferPointer(start: polygonFearure.coordinates,
                                                     count: Int(polygonFearure.pointCount)))
                .map { Vector2(x: $0.longitude, y: $0.latitude) }
            guard let height = polygonFearure.attributes["height"] as? Double else { return nil }
            return ExtrudedPolygon(id: Int32(index),
                                   vertices: vertices,
                                   altitude: height,
                                   payload: nil)
        }
        guard
            let clickedPolygon = mapViewport.getIntersectedPolygon(polygons: extrudedPolygons,
                                                                   point: Vector2(x: Double(point.x),
                                                                                  y: Double(point.y)))
        else {
            return
        }

        let polygons = mapViewport.projectPlanes(extrudedPolygon: clickedPolygon)
        debugOverlayView.clear()
        debugOverlayView.polygons.append(contentsOf: polygons)
        debugOverlayView.points.append(Vector2(x: Double(point.x),
                                               y: Double(point.y)))
        debugOverlayView.setNeedsDisplay()
    }
}

extension ViewController: MGLMapViewDelegate {
    func mapView(_ mapView: MGLMapView, didFinishLoading style: MGLStyle) {
        mapView.setCamera(MGLMapCamera(lookingAtCenter: kyivCoordinates,
                                       altitude: 400,
                                       pitch: 60,
                                       heading: 0),
                          animated: false)
    }
}
