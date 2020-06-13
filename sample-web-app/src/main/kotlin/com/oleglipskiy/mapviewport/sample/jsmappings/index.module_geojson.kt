@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")

typealias GeoJsonGeometryTypes = Any

typealias Position = Array<Number>

external interface GeoJsonObject<T> {
    var type: T
    var bbox: dynamic /* dynamic | dynamic */
        get() = definedExternally
        set(value) = definedExternally
}

typealias GeometryObject = Any

external interface Point : GeoJsonObject<String> {
    override var type: String /* "Point" */
    var coordinates: Position
}

external interface MultiPoint : GeoJsonObject<String> {
    override var type: String /* "MultiPoint" */
    var coordinates: Array<Position>
}

external interface LineString : GeoJsonObject<String> {
    override var type: String /* "LineString" */
    var coordinates: Array<Position>
}

external interface MultiLineString : GeoJsonObject<String> {
    override var type: String /* "MultiLineString" */
    var coordinates: Array<Array<Position>>
}

external interface Polygon : GeoJsonObject<String> {
    override var type: String /* "Polygon" */
    var coordinates: Array<Array<Position>>
}

external interface MultiPolygon : GeoJsonObject<String> {
    override var type: String /* "MultiPolygon" */
    var coordinates: Array<Array<Array<Position>>>
}

external interface GeometryCollection : GeoJsonObject<String> {
    override var type: String /* "GeometryCollection" */
    var geometries: Array<dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */>
}

external interface Feature<G, P> : GeoJsonObject<String> {
    override var type: String /* "Feature" */
    var geometry: G
    var id: dynamic /* String | Number */
        get() = definedExternally
        set(value) = definedExternally
    var properties: P
}

external interface FeatureCollection<G, P> : GeoJsonObject<String> {
    override var type: String /* "FeatureCollection" */
    var features: Array<Feature<G, P>>
}