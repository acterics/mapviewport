//
//  MapProjectionOverlayView.swift
//  SampleIOSApp
//
//  Created by Oleg Lipskiy on 21.06.2020.
//  Copyright © 2020 Oleg Lipskiy. All rights reserved.
//

import Foundation
import MapViewport
import UIKit

typealias Polygon = [Vector2]

final class MapProjectionOverlayView: UIView {
    var polygons: [Polygon] = [] {
        didSet {
            paths = polygons.compactMap { polygon in
                buildPolygon(polygon.map { CGPoint(x: $0.x, y: $0.y) })
            }
        }
    }

    var points: [Vector2] = []

    private var paths: [CGPath] = []

    override func draw(_ rect: CGRect) {
        guard let context = UIGraphicsGetCurrentContext() else { return }
        context.setLineWidth(1)
        context.setFillColor(red: 1, green: 0, blue: 0, alpha: 1)
        context.setStrokeColor(UIColor.red.cgColor)
        points.forEach { point in
            context.addEllipse(in: CGRect(x: point.x - 0.5,
                                          y: point.y - 0.5,
                                          width: 1, height: 1))
        }
        paths.forEach { path in
            context.addPath(path)
        }
        context.drawPath(using: .fillStroke)
    }

    func clear() {
        polygons = []
        points = []
        setNeedsDisplay()
    }

    private func buildPolygon(_ points: [CGPoint]) -> CGPath? {
        guard let first = points.first else { return nil }
        let bezierPath = UIBezierPath()
        bezierPath.move(to: first)
        points.dropFirst().forEach { point in
            bezierPath.addLine(to: point)
            bezierPath.move(to: point)
        }
        bezierPath.addLine(to: first)
        bezierPath.close()
        return bezierPath.cgPath
    }
}
