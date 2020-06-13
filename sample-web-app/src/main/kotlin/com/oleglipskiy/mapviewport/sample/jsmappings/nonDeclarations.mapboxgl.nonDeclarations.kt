@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package mapboxgl

typealias TransformRequestFunction = (url: String, resourceType: String /* 'Unknown' | 'Style' | 'Source' | 'Tile' | 'Glyphs' | 'SpriteImage' | 'SpriteJSON' | 'Image' */) -> RequestParameters