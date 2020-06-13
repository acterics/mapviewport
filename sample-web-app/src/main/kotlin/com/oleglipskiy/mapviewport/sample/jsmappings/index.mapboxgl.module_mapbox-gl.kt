@file:JsModule("mapbox-gl")
@file:JsNonModule
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package mapboxgl

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*
import Feature
import FeatureCollection

external var accessToken: String

external var version: String

external var baseApiUrl: String

external var workerCount: Number

external var maxParallelImageRequests: Number

external interface `T$0` {
    var failIfMajorPerformanceCaveat: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external fun supported(options: `T$0` = definedExternally): Boolean

external fun clearStorage(callback: (err: Error) -> Unit = definedExternally)

external fun setRTLTextPlugin(pluginURL: String, callback: (error: Error) -> Unit, deferred: Boolean = definedExternally)

external fun getRTLTextPluginStatus(): String /* 'unavailable' | 'loading' | 'loaded' | 'error' */

external fun prewarm()

external fun clearPrewarmedResources()

external interface `T$13` {
    var lng: Number
    var lat: Number
}

external interface `T$14` {
    var lon: Number
    var lat: Number
}

external interface `T$1` {
    var layers: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var filter: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var validate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$2` {
    var sourceLayer: String?
        get() = definedExternally
        set(value) = definedExternally
    var filter: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var validate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$3` {
    var diff: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var localIdeographFontFamily: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$4` {
    var width: Number
    var height: Number
    var data: dynamic /* Uint8Array | Uint8ClampedArray */
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$5` {
    var pixelRatio: Number?
        get() = definedExternally
        set(value) = definedExternally
    var sdf: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external open class Map(options: dynamic = definedExternally) : Evented {
    open fun addControl(control: Control, position: String = definedExternally): Map /* this */
    open fun addControl(control: IControl, position: String = definedExternally): Map /* this */
    open fun removeControl(control: Control): Map /* this */
    open fun removeControl(control: IControl): Map /* this */
    open fun resize(eventData: EventData = definedExternally): Map /* this */
    open fun getBounds(): LngLatBounds
    open fun getMaxBounds(): LngLatBounds?
    open fun setMaxBounds(lnglatbounds: LngLatBounds = definedExternally): Map /* this */
    open fun setMaxBounds(lnglatbounds: dynamic = definedExternally): Map /* this */
    open fun setMinZoom(minZoom: Number = definedExternally): Map /* this */
    open fun getMinZoom(): Number
    open fun setMaxZoom(maxZoom: Number = definedExternally): Map /* this */
    open fun getMaxZoom(): Number
    open fun getRenderWorldCopies(): Boolean
    open fun setRenderWorldCopies(renderWorldCopies: Boolean = definedExternally): Map /* this */
    open fun project(lnglat: LngLat): Point
    open fun project(lnglat: `T$13`): Point
    open fun project(lnglat: `T$14`): Point
    open fun project(lnglat: dynamic /* JsTuple<Number, Number> */): Point
    open fun unproject(point: Point): LngLat
    open fun unproject(point: dynamic /* JsTuple<Number, Number> */): LngLat
    open fun isMoving(): Boolean
    open fun isZooming(): Boolean
    open fun isRotating(): Boolean
    open fun queryRenderedFeatures(pointOrBox: Point = definedExternally, options: `T$1` = definedExternally): Array<Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */> /* GeoJSON.Feature<GeoJSON.Geometry> & `T$15` */>
    open fun queryRenderedFeatures(pointOrBox: dynamic = definedExternally, options: `T$1` = definedExternally): Array<Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */> /* GeoJSON.Feature<GeoJSON.Geometry> & `T$15` */>
    open fun querySourceFeatures(sourceID: String, parameters: `T$2` = definedExternally): Array<Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */> /* GeoJSON.Feature<GeoJSON.Geometry> & `T$15` */>
    open fun setStyle(style: Style, options: `T$3` = definedExternally): Map /* this */
    open fun setStyle(style: String, options: `T$3` = definedExternally): Map /* this */
    open fun getStyle(): Style
    open fun isStyleLoaded(): Boolean
    open fun addSource(id: String, source: GeoJSONSourceRaw): Map /* this */
    open fun addSource(id: String, source: VideoSourceRaw): Map /* this */
    open fun addSource(id: String, source: ImageSourceRaw): Map /* this */
    open fun addSource(id: String, source: CanvasSourceRaw): Map /* this */
    open fun addSource(id: String, source: VectorSource): Map /* this */
    open fun addSource(id: String, source: RasterSource): Map /* this */
    open fun addSource(id: String, source: RasterDemSource): Map /* this */
    open fun isSourceLoaded(id: String): Boolean
    open fun areTilesLoaded(): Boolean
    open fun removeSource(id: String): Map /* this */
    open fun getSource(id: String): dynamic /* GeoJSONSource | VideoSource | ImageSource | CanvasSource | VectorSource | RasterSource | RasterDemSource */
    open fun addImage(name: String, image: HTMLImageElement, options: `T$5` = definedExternally): Map /* this */
    open fun addImage(name: String, image: ArrayBufferView, options: `T$5` = definedExternally): Map /* this */
    open fun addImage(name: String, image: `T$4`, options: `T$5` = definedExternally): Map /* this */
    open fun addImage(name: String, image: ImageData, options: `T$5` = definedExternally): Map /* this */
    open fun hasImage(name: String): Boolean
    open fun removeImage(name: String): Map /* this */
    open fun loadImage(url: String, callback: Function<*>): Map /* this */
    open fun listImages(): Array<String>
    open fun addLayer(layer: Layer, before: String = definedExternally): Map /* this */
    open fun addLayer(layer: CustomLayerInterface, before: String = definedExternally): Map /* this */
    open fun moveLayer(id: String, beforeId: String = definedExternally): Map /* this */
    open fun removeLayer(id: String): Map /* this */
    open fun getLayer(id: String): Layer
    open fun setFilter(layer: String, filter: Array<Any> = definedExternally): Map /* this */
    open fun setFilter(layer: String, filter: Boolean = definedExternally): Map /* this */
    open fun setFilter(layer: String, filter: Nothing? = definedExternally): Map /* this */
    open fun setLayerZoomRange(layerId: String, minzoom: Number, maxzoom: Number): Map /* this */
    open fun getFilter(layer: String): Array<Any>
    open fun setPaintProperty(layer: String, name: String, value: Any, klass: String = definedExternally): Map /* this */
    open fun getPaintProperty(layer: String, name: String): Any
    open fun setLayoutProperty(layer: String, name: String, value: Any): Map /* this */
    open fun getLayoutProperty(layer: String, name: String): Any
    open fun setLight(options: Light, lightOptions: Any = definedExternally): Map /* this */
    open fun getLight(): Light
    open fun setFeatureState(feature: FeatureIdentifier, state: Json)
    open fun setFeatureState(feature: Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */> /* GeoJSON.Feature<GeoJSON.Geometry> & `T$15` */, state: Json)
    open fun getFeatureState(feature: FeatureIdentifier): Json
    open fun getFeatureState(feature: Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */> /* GeoJSON.Feature<GeoJSON.Geometry> & `T$15` */): Json
    open fun removeFeatureState(target: FeatureIdentifier, key: String = definedExternally)
    open fun removeFeatureState(target: Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */> /* GeoJSON.Feature<GeoJSON.Geometry> & `T$15` */, key: String = definedExternally)
    open fun getContainer(): HTMLElement
    open fun getCanvasContainer(): HTMLElement
    open fun getCanvas(): HTMLCanvasElement
    open fun loaded(): Boolean
    open fun remove()
    open fun triggerRepaint()
    open var showTileBoundaries: Boolean
    open var showCollisionBoxes: Boolean
    open var showPadding: Boolean
    open var repaint: Boolean
    open fun getCenter(): LngLat
    open fun setCenter(center: LngLat, eventData: EventData = definedExternally): Map /* this */
    open fun setCenter(center: `T$13`, eventData: EventData = definedExternally): Map /* this */
    open fun setCenter(center: `T$14`, eventData: EventData = definedExternally): Map /* this */
    open fun setCenter(center: dynamic /* JsTuple<Number, Number> */, eventData: EventData = definedExternally): Map /* this */
    open fun panBy(offset: Point, options: AnimationOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun panBy(offset: dynamic /* JsTuple<Number, Number> */, options: AnimationOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun panTo(lnglat: LngLat, options: AnimationOptions = definedExternally, eventdata: EventData = definedExternally): Map /* this */
    open fun panTo(lnglat: `T$13`, options: AnimationOptions = definedExternally, eventdata: EventData = definedExternally): Map /* this */
    open fun panTo(lnglat: `T$14`, options: AnimationOptions = definedExternally, eventdata: EventData = definedExternally): Map /* this */
    open fun panTo(lnglat: dynamic /* JsTuple<Number, Number> */, options: AnimationOptions = definedExternally, eventdata: EventData = definedExternally): Map /* this */
    open fun getZoom(): Number
    open fun setZoom(zoom: Number, eventData: EventData = definedExternally): Map /* this */
    open fun zoomTo(zoom: Number, options: AnimationOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun zoomIn(options: AnimationOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun zoomOut(options: AnimationOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun getBearing(): Number
    open fun setBearing(bearing: Number, eventData: EventData = definedExternally): Map /* this */
    open fun getPadding(): PaddingOptions
    open fun setPadding(padding: PaddingOptions, eventData: EventData = definedExternally): Map /* this */
    open fun rotateTo(bearing: Number, options: AnimationOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun resetNorth(options: AnimationOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun snapToNorth(options: AnimationOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun getPitch(): Number
    open fun setPitch(pitch: Number, eventData: EventData = definedExternally): Map /* this */
    open fun cameraForBounds(bounds: LngLatBounds, options: CameraForBoundsOptions = definedExternally): Any /* Required<Pick<CameraOptions, String /* 'zoom' | 'bearing' */>> & `T$17` */
    open fun cameraForBounds(bounds: dynamic, options: CameraForBoundsOptions = definedExternally): Any /* Required<Pick<CameraOptions, String /* 'zoom' | 'bearing' */>> & `T$17` */
    open fun fitBounds(bounds: LngLatBounds, options: FitBoundsOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun fitBounds(bounds: dynamic, options: FitBoundsOptions = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun fitScreenCoordinates(p0: Point, p1: Point, bearing: Number, options: AnimationOptions /* AnimationOptions & CameraOptions */ = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun fitScreenCoordinates(p0: Point, p1: dynamic /* JsTuple<Number, Number> */, bearing: Number, options: AnimationOptions /* AnimationOptions & CameraOptions */ = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun fitScreenCoordinates(p0: dynamic /* JsTuple<Number, Number> */, p1: Point, bearing: Number, options: AnimationOptions /* AnimationOptions & CameraOptions */ = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun fitScreenCoordinates(p0: dynamic /* JsTuple<Number, Number> */, p1: dynamic /* JsTuple<Number, Number> */, bearing: Number, options: AnimationOptions /* AnimationOptions & CameraOptions */ = definedExternally, eventData: EventData = definedExternally): Map /* this */
    open fun jumpTo(options: CameraOptions, eventData: EventData = definedExternally): Map /* this */
    open fun easeTo(options: EaseToOptions, eventData: EventData = definedExternally): Map /* this */
    open fun flyTo(options: FlyToOptions, eventData: EventData = definedExternally): Map /* this */
    open fun isEasing(): Boolean
    open fun stop(): Map /* this */
    open fun <T : Any> on(type: T, layer: String, listener: (ev: Any /* Any & EventData */) -> Unit): Map /* this */
    open fun <T : Any> on(type: T, listener: (ev: Any /* Any & EventData */) -> Unit): Map /* this */
    open fun on(type: String, listener: (ev: MapEvent) -> Unit): Map /* this */
    open fun <T : Any> once(type: T, layer: String, listener: (ev: Any /* Any & EventData */) -> Unit): Map /* this */
    open fun <T : Any> once(type: T, listener: (ev: Any /* Any & EventData */) -> Unit): Map /* this */
    open fun once(type: String, listener: (ev: Any) -> Unit): Map /* this */
    open fun <T : Any> off(type: T, layer: String, listener: (ev: Any /* Any & EventData */) -> Unit): Map /* this */
    open fun <T : Any> off(type: T, listener: (ev: Any /* Any & EventData */) -> Unit): Map /* this */
    open fun off(type: String, listener: (ev: Any) -> Unit): Map /* this */
    open var scrollZoom: ScrollZoomHandler
    open var boxZoom: BoxZoomHandler
    open var dragRotate: DragRotateHandler
    open var dragPan: DragPanHandler
    open var keyboard: KeyboardHandler
    open var doubleClickZoom: DoubleClickZoomHandler
    open var touchZoomRotate: TouchZoomRotateHandler
    open var touchPitch: TouchPitchHandler
    open fun addControl(control: Control): Map /* this */
    open fun addControl(control: IControl): Map /* this */
    open fun setMaxBounds(): Map /* this */
    open fun queryRenderedFeatures(): Array<Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */> /* GeoJSON.Feature<GeoJSON.Geometry> & `T$15` */>
    open fun setFilter(layer: String): Map /* this */
}

external interface `T$6` {
    @nativeGetter
    operator fun get(key: String): String?
    @nativeSetter
    operator fun set(key: String, value: String)
}

external interface MapboxOptions {
    var antialias: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var attributionControl: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var bearing: Number?
        get() = definedExternally
        set(value) = definedExternally
    var bearingSnap: Number?
        get() = definedExternally
        set(value) = definedExternally
    var bounds: dynamic /* LngLatBounds | dynamic | dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var boxZoom: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var center: dynamic /* LngLat | `T$13` | `T$14` | dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var clickTolerance: Number?
        get() = definedExternally
        set(value) = definedExternally
    var collectResourceTiming: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var crossSourceCollisions: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var container: dynamic /* String | HTMLElement */
        get() = definedExternally
        set(value) = definedExternally
    var customAttribution: dynamic /* String | Array<String> */
        get() = definedExternally
        set(value) = definedExternally
    var dragPan: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var dragRotate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var doubleClickZoom: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var hash: dynamic /* Boolean | String */
        get() = definedExternally
        set(value) = definedExternally
    var fadeDuration: Number?
        get() = definedExternally
        set(value) = definedExternally
    var failIfMajorPerformanceCaveat: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var fitBoundsOptions: FitBoundsOptions?
        get() = definedExternally
        set(value) = definedExternally
    var interactive: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var keyboard: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var locale: `T$6`?
        get() = definedExternally
        set(value) = definedExternally
    var localIdeographFontFamily: String?
        get() = definedExternally
        set(value) = definedExternally
    var logoPosition: String /* 'top-left' | 'top-right' | 'bottom-left' | 'bottom-right' */
    var maxBounds: dynamic /* LngLatBounds | dynamic | dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var maxPitch: Number?
        get() = definedExternally
        set(value) = definedExternally
    var maxZoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var minPitch: Number?
        get() = definedExternally
        set(value) = definedExternally
    var minZoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var preserveDrawingBuffer: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var pitch: Number?
        get() = definedExternally
        set(value) = definedExternally
    var pitchWithRotate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var refreshExpiredTiles: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var renderWorldCopies: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var scrollZoom: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var style: dynamic /* mapboxgl.Style | String */
        get() = definedExternally
        set(value) = definedExternally
    var trackResize: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var transformRequest: TransformRequestFunction?
        get() = definedExternally
        set(value) = definedExternally
    var touchZoomRotate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var touchPitch: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var zoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var maxTileCacheSize: Number?
        get() = definedExternally
        set(value) = definedExternally
    var accessToken: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RequestParameters {
    var url: String
    var credentials: String /* 'same-origin' | 'include' */
    var headers: Json?
        get() = definedExternally
        set(value) = definedExternally
    var method: String /* 'GET' | 'POST' | 'PUT' */
    var collectResourceTiming: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PaddingOptions {
    var top: Number
    var bottom: Number
    var left: Number
    var right: Number
}

external interface FeatureIdentifier {
    var id: dynamic /* String | Number */
        get() = definedExternally
        set(value) = definedExternally
    var source: String
    var sourceLayer: String?
        get() = definedExternally
        set(value) = definedExternally
}

external open class BoxZoomHandler(map: mapboxgl.Map) {
    open fun isEnabled(): Boolean
    open fun isActive(): Boolean
    open fun enable()
    open fun disable()
}

external open class ScrollZoomHandler(map: mapboxgl.Map) {
    open fun isEnabled(): Boolean
    open fun enable()
    open fun disable()
    open fun setZoomRate(zoomRate: Number)
    open fun setWheelZoomRate(wheelZoomRate: Number)
}

external open class DragPanHandler(map: mapboxgl.Map) {
    open fun isEnabled(): Boolean
    open fun isActive(): Boolean
    open fun enable()
    open fun disable()
}

external interface `T$7` {
    var bearingSnap: Number?
        get() = definedExternally
        set(value) = definedExternally
    var pitchWithRotate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external open class DragRotateHandler(map: mapboxgl.Map, options: `T$7` = definedExternally) {
    open fun isEnabled(): Boolean
    open fun isActive(): Boolean
    open fun enable()
    open fun disable()
}

external open class KeyboardHandler(map: mapboxgl.Map) {
    open fun isEnabled(): Boolean
    open fun enable()
    open fun disable()
}

external open class DoubleClickZoomHandler(map: mapboxgl.Map) {
    open fun isEnabled(): Boolean
    open fun enable()
    open fun disable()
}

external open class TouchZoomRotateHandler(map: mapboxgl.Map) {
    open fun isEnabled(): Boolean
    open fun enable()
    open fun disable()
    open fun disableRotation()
    open fun enableRotation()
}

external open class TouchPitchHandler(map: mapboxgl.Map) {
    open fun enable()
    open fun isActive(): Boolean
    open fun isEnabled(): Boolean
    open fun disable()
}

external interface IControl {
    fun onAdd(map: Map): HTMLElement
    fun onRemove(map: Map): Any
    var getDefaultPosition: (() -> String)?
        get() = definedExternally
        set(value) = definedExternally
}

external open class Control : Evented

external interface `T$8` {
    var showCompass: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showZoom: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var visualizePitch: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external open class NavigationControl(options: `T$8` = definedExternally) : Control

external open class PositionOptions {
    open var enableHighAccuracy: Boolean
    open var timeout: Number
    open var maximumAge: Number
}

external interface `T$9` {
    var positionOptions: PositionOptions?
        get() = definedExternally
        set(value) = definedExternally
    var fitBoundsOptions: FitBoundsOptions?
        get() = definedExternally
        set(value) = definedExternally
    var trackUserLocation: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showAccuracyCircle: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showUserLocation: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external open class GeolocateControl(options: `T$9` = definedExternally) : Control {
    open fun trigger(): Boolean
}

external interface `T$10` {
    var compact: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var customAttribution: dynamic /* String | Array<String> */
        get() = definedExternally
        set(value) = definedExternally
}

external open class AttributionControl(options: `T$10` = definedExternally) : Control

external interface `T$11` {
    var maxWidth: Number?
        get() = definedExternally
        set(value) = definedExternally
    var unit: String?
        get() = definedExternally
        set(value) = definedExternally
}

external open class ScaleControl(options: `T$11` = definedExternally) : Control {
    open fun setUnit(unit: String)
}

external open class FullscreenControl(options: FullscreenControlOptions? = definedExternally) : Control

external interface FullscreenControlOptions {
    var container: HTMLElement?
        get() = definedExternally
        set(value) = definedExternally
}

external open class Popup(options: mapboxgl.PopupOptions = definedExternally) : Evented {
    open fun addTo(map: Map): Popup /* this */
    open fun isOpen(): Boolean
    open fun remove(): Popup /* this */
    open fun getLngLat(): LngLat
    open fun setLngLat(lnglat: LngLat): Popup /* this */
    open fun setLngLat(lnglat: `T$13`): Popup /* this */
    open fun setLngLat(lnglat: `T$14`): Popup /* this */
    open fun setLngLat(lnglat: dynamic /* JsTuple<Number, Number> */): Popup /* this */
    open fun trackPointer(): Popup /* this */
    open fun getElement(): HTMLElement
    open fun setText(text: String): Popup /* this */
    open fun setHTML(html: String): Popup /* this */
    open fun setDOMContent(htmlNode: Node): Popup /* this */
    open fun getMaxWidth(): String
    open fun setMaxWidth(maxWidth: String): Popup /* this */
    open fun addClassName(className: String)
    open fun removeClassName(className: String)
    open fun toggleClassName(className: String)
}

external interface `T$12` {
    @nativeGetter
    operator fun get(key: String): dynamic /* Point | dynamic */
    @nativeSetter
    operator fun set(key: String, value: Point)
    @nativeSetter
    operator fun set(key: String, value: dynamic /* JsTuple<Number, Number> */)
}

external interface PopupOptions {
    var closeButton: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var closeOnClick: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var closeOnMove: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var anchor: String /* 'center' | 'left' | 'right' | 'top' | 'bottom' | 'top-left' | 'top-right' | 'bottom-left' | 'bottom-right' */
    var offset: dynamic /* Number | Point | dynamic | `T$12` */
        get() = definedExternally
        set(value) = definedExternally
    var className: String?
        get() = definedExternally
        set(value) = definedExternally
    var maxWidth: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Style {
    var bearing: Number?
        get() = definedExternally
        set(value) = definedExternally
    var center: Array<Number>?
        get() = definedExternally
        set(value) = definedExternally
    var glyphs: String?
        get() = definedExternally
        set(value) = definedExternally
    var layers: Array<Layer>?
        get() = definedExternally
        set(value) = definedExternally
    var metadata: Any?
        get() = definedExternally
        set(value) = definedExternally
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var pitch: Number?
        get() = definedExternally
        set(value) = definedExternally
    var light: Light?
        get() = definedExternally
        set(value) = definedExternally
    var sources: Sources?
        get() = definedExternally
        set(value) = definedExternally
    var sprite: String?
        get() = definedExternally
        set(value) = definedExternally
    var transition: Transition?
        get() = definedExternally
        set(value) = definedExternally
    var version: Number
    var zoom: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Transition {
    var delay: Number?
        get() = definedExternally
        set(value) = definedExternally
    var duration: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Light {
    operator fun get(key: String): Transition?
    operator fun set(key: String, value: Transition?)
    var anchor: String /* 'map' | 'viewport' */
    var position: Array<Number>?
        get() = definedExternally
        set(value) = definedExternally
    var color: String?
        get() = definedExternally
        set(value) = definedExternally
    var intensity: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Sources {
    @nativeGetter
    operator fun get(sourceName: String): dynamic /* GeoJSONSourceRaw | VideoSourceRaw | ImageSourceRaw | CanvasSourceRaw | VectorSource | RasterSource | RasterDemSource */
    @nativeSetter
    operator fun set(sourceName: String, value: GeoJSONSourceRaw)
    @nativeSetter
    operator fun set(sourceName: String, value: VideoSourceRaw)
    @nativeSetter
    operator fun set(sourceName: String, value: ImageSourceRaw)
    @nativeSetter
    operator fun set(sourceName: String, value: CanvasSourceRaw)
    @nativeSetter
    operator fun set(sourceName: String, value: VectorSource)
    @nativeSetter
    operator fun set(sourceName: String, value: RasterSource)
    @nativeSetter
    operator fun set(sourceName: String, value: RasterDemSource)
}

external interface Source {
    var type: String /* 'vector' | 'raster' | 'raster-dem' | 'geojson' | 'image' | 'video' | 'canvas' */
}

external interface GeoJSONSourceRaw : Source, GeoJSONSourceOptions {
    override var type: String /* 'geojson' */
}

external open class GeoJSONSource(options: mapboxgl.GeoJSONSourceOptions = definedExternally) : GeoJSONSourceRaw {
    override var type: String /* 'geojson' */
    open fun setData(data: Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */>): GeoJSONSource /* this */
    open fun setData(data: FeatureCollection<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */>): GeoJSONSource /* this */
    open fun setData(data: String): GeoJSONSource /* this */
    open fun getClusterExpansionZoom(clusterId: Number, callback: (error: Any, zoom: Number) -> Unit): GeoJSONSource /* this */
    open fun getClusterChildren(clusterId: Number, callback: (error: Any, features: Array<Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */>>) -> Unit): GeoJSONSource /* this */
    open fun getClusterLeaves(cluserId: Number, limit: Number, offset: Number, callback: (error: Any, features: Array<Feature<dynamic, dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */>>) -> Unit): GeoJSONSource /* this */
}

external interface GeoJSONSourceOptions {
    var data: dynamic /* GeoJSON.Feature<dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */> | GeoJSON.FeatureCollection<dynamic /* Point | MultiPoint | LineString | MultiLineString | Polygon | MultiPolygon | GeometryCollection */> | String */
        get() = definedExternally
        set(value) = definedExternally
    var maxzoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var attribution: String?
        get() = definedExternally
        set(value) = definedExternally
    var buffer: Number?
        get() = definedExternally
        set(value) = definedExternally
    var tolerance: Number?
        get() = definedExternally
        set(value) = definedExternally
    var cluster: dynamic /* Number | Boolean */
        get() = definedExternally
        set(value) = definedExternally
    var clusterRadius: Number?
        get() = definedExternally
        set(value) = definedExternally
    var clusterMaxZoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var clusterProperties: Any?
        get() = definedExternally
        set(value) = definedExternally
    var lineMetrics: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var generateId: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var promoteId: dynamic /* `T$6` | String */
        get() = definedExternally
        set(value) = definedExternally
}

external interface VideoSourceRaw : Source, VideoSourceOptions {
    override var type: String /* 'video' */
}

external open class VideoSource(options: mapboxgl.VideoSourceOptions = definedExternally) : VideoSourceRaw {
    override var type: String /* 'video' */
    open fun getVideo(): HTMLVideoElement
    open fun setCoordinates(coordinates: Array<Array<Number>>): VideoSource /* this */
}

external interface VideoSourceOptions {
    var urls: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var coordinates: Array<Array<Number>>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ImageSourceRaw : Source, ImageSourceOptions {
    override var type: String /* 'image' */
}

external open class ImageSource(options: mapboxgl.ImageSourceOptions = definedExternally) : ImageSourceRaw {
    override var type: String /* 'image' */
    open fun updateImage(options: ImageSourceOptions): ImageSource /* this */
    open fun setCoordinates(coordinates: Array<Array<Number>>): ImageSource /* this */
}

external interface ImageSourceOptions {
    var url: String?
        get() = definedExternally
        set(value) = definedExternally
    var coordinates: Array<Array<Number>>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CanvasSourceRaw : Source, CanvasSourceOptions {
    override var type: String /* 'canvas' */
}

external open class CanvasSource : CanvasSourceRaw {
    override var type: String /* 'canvas' */
    override var coordinates: Array<Array<Number>>
    override var canvas: dynamic /* String | HTMLCanvasElement */
    open fun play()
    open fun pause()
    open fun getCanvas(): HTMLCanvasElement
    open fun setCoordinates(coordinates: Array<Array<Number>>): CanvasSource /* this */
}

external interface CanvasSourceOptions {
    var coordinates: Array<Array<Number>>
    var animate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var canvas: dynamic /* String | HTMLCanvasElement */
        get() = definedExternally
        set(value) = definedExternally
}

external interface VectorSource : Source {
    override var type: String /* 'vector' */
    var url: String?
        get() = definedExternally
        set(value) = definedExternally
    var tiles: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var bounds: Array<Number>?
        get() = definedExternally
        set(value) = definedExternally
    var scheme: String /* 'xyz' | 'tms' */
    var minzoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var maxzoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var attribution: String?
        get() = definedExternally
        set(value) = definedExternally
    var promoteId: dynamic /* `T$6` | String */
        get() = definedExternally
        set(value) = definedExternally
}

external interface RasterSource : Source {
    override var type: String /* 'raster' */
    var url: String?
        get() = definedExternally
        set(value) = definedExternally
    var tiles: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var bounds: Array<Number>?
        get() = definedExternally
        set(value) = definedExternally
    var minzoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var maxzoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var tileSize: Number?
        get() = definedExternally
        set(value) = definedExternally
    var scheme: String /* 'xyz' | 'tms' */
    var attribution: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RasterDemSource : Source {
    override var type: String /* 'raster-dem' */
    var url: String?
        get() = definedExternally
        set(value) = definedExternally
    var tiles: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var bounds: Array<Number>?
        get() = definedExternally
        set(value) = definedExternally
    var minzoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var maxzoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var tileSize: Number?
        get() = definedExternally
        set(value) = definedExternally
    var attribution: String?
        get() = definedExternally
        set(value) = definedExternally
    var encoding: String /* 'terrarium' | 'mapbox' */
}

external open class LngLat(lng: Number, lat: Number) {
    open var lng: Number
    open var lat: Number
    open fun wrap(): LngLat
    open fun toArray(): Array<Number>
    override fun toString(): String
    open fun distanceTo(lngLat: LngLat): Number
    open fun toBounds(radius: Number): LngLatBounds

    companion object {
        fun convert(input: LngLat): LngLat
        fun convert(input: `T$13`): LngLat
        fun convert(input: `T$14`): LngLat
        fun convert(input: dynamic /* JsTuple<Number, Number> */): LngLat
    }
}

external open class LngLatBounds {
    constructor(boundsLike: dynamic)
    constructor(boundsLike: dynamic)
    constructor(sw: LngLat, ne: dynamic)
    constructor(sw: `T$13`, ne: dynamic)
    constructor(sw: `T$14`, ne: dynamic)
    constructor(sw: dynamic, ne: dynamic)
    open var sw: dynamic /* LngLat | `T$13` | `T$14` | dynamic */
    open var ne: dynamic /* LngLat | `T$13` | `T$14` | dynamic */
    open fun setNorthEast(ne: LngLat): LngLatBounds /* this */
    open fun setNorthEast(ne: `T$13`): LngLatBounds /* this */
    open fun setNorthEast(ne: `T$14`): LngLatBounds /* this */
    open fun setNorthEast(ne: dynamic /* JsTuple<Number, Number> */): LngLatBounds /* this */
    open fun setSouthWest(sw: LngLat): LngLatBounds /* this */
    open fun setSouthWest(sw: `T$13`): LngLatBounds /* this */
    open fun setSouthWest(sw: `T$14`): LngLatBounds /* this */
    open fun setSouthWest(sw: dynamic /* JsTuple<Number, Number> */): LngLatBounds /* this */
    open fun contains(lnglat: LngLat): Boolean
    open fun contains(lnglat: `T$13`): Boolean
    open fun contains(lnglat: `T$14`): Boolean
    open fun contains(lnglat: dynamic /* JsTuple<Number, Number> */): Boolean
    open fun extend(obj: LngLat): LngLatBounds /* this */
    open fun extend(obj: `T$13`): LngLatBounds /* this */
    open fun extend(obj: `T$14`): LngLatBounds /* this */
    open fun extend(obj: dynamic): LngLatBounds /* this */
    open fun extend(obj: LngLatBounds): LngLatBounds /* this */
    open fun getCenter(): LngLat
    open fun getSouthWest(): LngLat
    open fun getNorthEast(): LngLat
    open fun getNorthWest(): LngLat
    open fun getSouthEast(): LngLat
    open fun getWest(): Number
    open fun getSouth(): Number
    open fun getEast(): Number
    open fun getNorth(): Number
    open fun toArray(): Array<Array<Number>>
    override fun toString(): String
    open fun isEmpty(): Boolean

    companion object {
        fun convert(input: LngLatBounds): LngLatBounds
        fun convert(input: dynamic /* JsTuple<dynamic, dynamic> */): LngLatBounds
        fun convert(input: dynamic /* JsTuple<Number, Number, Number, Number> */): LngLatBounds
    }
}

external open class Point(x: Number, y: Number) {
    open var x: Number
    open var y: Number
    open fun clone(): Point
    open fun add(p: Point): Point
    open fun sub(p: Point): Point
    open fun mult(k: Number): Point
    open fun div(k: Number): Point
    open fun rotate(a: Number): Point
    open fun matMult(m: Number): Point
    open fun unit(): Point
    open fun perp(): Point
    open fun round(): Point
    open fun mag(): Number
    open fun equals(p: Point): Boolean
    open fun dist(p: Point): Number
    open fun distSqr(p: Point): Number
    open fun angle(): Number
    open fun angleTo(p: Point): Number
    open fun angleWidth(p: Point): Number
    open fun angleWithSep(x: Number, y: Number): Number

    companion object {
        fun convert(a: Point): Point
        fun convert(a: dynamic /* JsTuple<Number, Number> */): Point
    }
}

external open class MercatorCoordinate(x: Number, y: Number, z: Number = definedExternally) {
    open var x: Number
    open var y: Number
    open var z: Number
    open fun toAltitude(): Number
    open fun toLngLat(): LngLat
    open fun meterInMercatorCoordinateUnits(): Number

    companion object {
        fun fromLngLat(lngLatLike: LngLat, altitude: Number = definedExternally): MercatorCoordinate
        fun fromLngLat(lngLatLike: `T$13`, altitude: Number = definedExternally): MercatorCoordinate
        fun fromLngLat(lngLatLike: `T$14`, altitude: Number = definedExternally): MercatorCoordinate
        fun fromLngLat(lngLatLike: dynamic /* JsTuple<Number, Number> */, altitude: Number = definedExternally): MercatorCoordinate
    }
}

external open class Marker(options: mapboxgl.MarkerOptions = definedExternally) : Evented {
    constructor(element: HTMLElement, options: MarkerOptions)
    open fun addTo(map: Map): Marker /* this */
    open fun remove(): Marker /* this */
    open fun getLngLat(): LngLat
    open fun setLngLat(lngLat: LngLat): Marker /* this */
    open fun setLngLat(lngLat: `T$13`): Marker /* this */
    open fun setLngLat(lngLat: `T$14`): Marker /* this */
    open fun setLngLat(lngLat: dynamic /* JsTuple<Number, Number> */): Marker /* this */
    open fun getElement(): HTMLElement
    open fun setPopup(popup: Popup = definedExternally): Marker /* this */
    open fun getPopup(): Popup
    open fun togglePopup(): Marker /* this */
    open fun getOffset(): dynamic /* Point | dynamic */
    open fun setOffset(offset: Point): Marker /* this */
    open fun setOffset(offset: dynamic /* JsTuple<Number, Number> */): Marker /* this */
    open fun setDraggable(shouldBeDraggable: Boolean): Marker /* this */
    open fun isDraggable(): Boolean
}

external interface MarkerOptions {
    var element: HTMLElement?
        get() = definedExternally
        set(value) = definedExternally
    var offset: dynamic /* Point | dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var anchor: String /* 'center' | 'left' | 'right' | 'top' | 'bottom' | 'top-left' | 'top-right' | 'bottom-left' | 'bottom-right' */
    var color: String?
        get() = definedExternally
        set(value) = definedExternally
    var draggable: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var rotation: Number?
        get() = definedExternally
        set(value) = definedExternally
    var rotationAlignment: String /* 'map' | 'viewport' | 'auto' */
    var pitchAlignment: String /* 'map' | 'viewport' | 'auto' */
}

external open class Evented {
    open fun on(type: String, listener: Function<*>): Evented /* this */
    open fun off(type: String = definedExternally, listener: Function<*> = definedExternally): Evented /* this */
    open fun off(type: Any = definedExternally, listener: Function<*> = definedExternally): Evented /* this */
    open fun once(type: String, listener: Function<*>): Evented /* this */
    open fun fire(type: String, properties: Json = definedExternally): Evented /* this */
    open fun off(): Evented /* this */
}

external interface StyleOptions {
    var transition: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EventData {
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}

external open class MapboxEvent<TOrig> {
    open var type: String
    open var target: Map
    open var originalEvent: TOrig
}

external open class MapMouseEvent : MapboxEvent<MouseEvent> {
    override var type: String /* 'mousedown' | 'mouseup' | 'click' | 'dblclick' | 'mousemove' | 'mouseover' | 'mouseenter' | 'mouseleave' | 'mouseout' | 'contextmenu' */
    open var point: Point
    open var lngLat: LngLat
    open fun preventDefault()
    open var defaultPrevented: Boolean
}

external open class MapTouchEvent : MapboxEvent<TouchEvent> {
    override var type: String /* 'touchstart' | 'touchend' | 'touchcancel' */
    open var point: Point
    open var lngLat: LngLat
    open var points: Array<Point>
    open var lngLats: Array<LngLat>
    open fun preventDefault()
    open var defaultPrevented: Boolean
}

external open class MapWheelEvent : MapboxEvent<WheelEvent> {
    override var type: String /* 'wheel' */
    open fun preventDefault()
    open var defaultPrevented: Boolean
}

external interface MapBoxZoomEvent : MapboxEvent<MouseEvent> {
    override var type: String /* 'boxzoomstart' | 'boxzoomend' | 'boxzoomcancel' */
    var boxZoomBounds: LngLatBounds
}

external interface MapStyleDataEvent : MapboxEvent<Nothing?> {
    var dataType: String /* 'style' */
}

external interface MapSourceDataEvent : MapboxEvent<Nothing?> {
    var dataType: String /* 'source' */
    var isSourceLoaded: Boolean
    var source: Source
    var sourceId: String
    var sourceDataType: String /* 'metadata' | 'content' */
    var tile: Any
    var coord: Coordinate
}

external interface Coordinate {
    var canonical: CanonicalCoordinate
    var wrap: Number
    var key: Number
}

external interface CanonicalCoordinate {
    var x: Number
    var y: Number
    var z: Number
    var key: Number
    fun equals(coord: CanonicalCoordinate): Boolean
}

external interface MapContextEvent : MapboxEvent<WebGLContextEvent> {
    override var type: String /* 'webglcontextlost' | 'webglcontextrestored' */
}

external open class ErrorEvent : MapboxEvent<Nothing?> {
    override var type: String /* 'error' */
    open var error: Error
}

external interface AnimationOptions {
    var duration: Number?
        get() = definedExternally
        set(value) = definedExternally
    var easing: ((time: Number) -> Number)?
        get() = definedExternally
        set(value) = definedExternally
    var offset: dynamic /* Point | dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var animate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var essential: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CameraOptions {
    var center: dynamic /* LngLat | `T$13` | `T$14` | dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var zoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var bearing: Number?
        get() = definedExternally
        set(value) = definedExternally
    var pitch: Number?
        get() = definedExternally
        set(value) = definedExternally
    var around: dynamic /* LngLat | `T$13` | `T$14` | dynamic */
        get() = definedExternally
        set(value) = definedExternally
}

external interface CameraForBoundsOptions : CameraOptions {
    var padding: dynamic /* Number | PaddingOptions */
        get() = definedExternally
        set(value) = definedExternally
    var offset: dynamic /* Point | dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var maxZoom: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface FlyToOptions : AnimationOptions, CameraOptions {
    var curve: Number?
        get() = definedExternally
        set(value) = definedExternally
    var minZoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var speed: Number?
        get() = definedExternally
        set(value) = definedExternally
    var screenSpeed: Number?
        get() = definedExternally
        set(value) = definedExternally
    var maxDuration: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EaseToOptions : AnimationOptions, CameraOptions {
    var delayEndEvents: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface FitBoundsOptions : FlyToOptions {
    var linear: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var padding: dynamic /* Number | mapboxgl.PaddingOptions */
        get() = definedExternally
        set(value) = definedExternally
    override var offset: dynamic /* Point | dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var maxZoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    override var maxDuration: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MapEventType {
    var error: ErrorEvent
    var load: MapboxEvent<Nothing?>
    var remove: MapboxEvent<Nothing?>
    var render: MapboxEvent<Nothing?>
    var resize: MapboxEvent<Nothing?>
    var webglcontextlost: MapContextEvent
    var webglcontextrestored: MapContextEvent
    var dataloading: dynamic /* MapSourceDataEvent | MapStyleDataEvent */
        get() = definedExternally
        set(value) = definedExternally
    var data: dynamic /* MapSourceDataEvent | MapStyleDataEvent */
        get() = definedExternally
        set(value) = definedExternally
    var tiledataloading: dynamic /* MapSourceDataEvent | MapStyleDataEvent */
        get() = definedExternally
        set(value) = definedExternally
    var sourcedataloading: MapSourceDataEvent
    var styledataloading: MapStyleDataEvent
    var sourcedata: MapSourceDataEvent
    var styledata: MapStyleDataEvent
    var boxzoomcancel: MapBoxZoomEvent
    var boxzoomstart: MapBoxZoomEvent
    var boxzoomend: MapBoxZoomEvent
    var touchcancel: MapTouchEvent
    var touchmove: MapTouchEvent
    var touchend: MapTouchEvent
    var touchstart: MapTouchEvent
    var click: MapMouseEvent
    var contextmenu: MapMouseEvent
    var dblclick: MapMouseEvent
    var mousemove: MapMouseEvent
    var mouseup: MapMouseEvent
    var mousedown: MapMouseEvent
    var mouseout: MapMouseEvent
    var mouseover: MapMouseEvent
    var movestart: MapboxEvent<dynamic /* MouseEvent | TouchEvent | WheelEvent | Nothing? */>
    var move: MapboxEvent<dynamic /* MouseEvent | TouchEvent | WheelEvent | Nothing? */>
    var moveend: MapboxEvent<dynamic /* MouseEvent | TouchEvent | WheelEvent | Nothing? */>
    var zoomstart: MapboxEvent<dynamic /* MouseEvent | TouchEvent | WheelEvent | Nothing? */>
    var zoom: MapboxEvent<dynamic /* MouseEvent | TouchEvent | WheelEvent | Nothing? */>
    var zoomend: MapboxEvent<dynamic /* MouseEvent | TouchEvent | WheelEvent | Nothing? */>
    var rotatestart: MapboxEvent<dynamic /* MouseEvent | TouchEvent | Nothing? */>
    var rotate: MapboxEvent<dynamic /* MouseEvent | TouchEvent | Nothing? */>
    var rotateend: MapboxEvent<dynamic /* MouseEvent | TouchEvent | Nothing? */>
    var dragstart: MapboxEvent<dynamic /* MouseEvent | TouchEvent | Nothing? */>
    var drag: MapboxEvent<dynamic /* MouseEvent | TouchEvent | Nothing? */>
    var dragend: MapboxEvent<dynamic /* MouseEvent | TouchEvent | Nothing? */>
    var pitchstart: MapboxEvent<dynamic /* MouseEvent | TouchEvent | Nothing? */>
    var pitch: MapboxEvent<dynamic /* MouseEvent | TouchEvent | Nothing? */>
    var pitchend: MapboxEvent<dynamic /* MouseEvent | TouchEvent | Nothing? */>
    var wheel: MapWheelEvent
}

external interface MapLayerEventType {
    var click: MapMouseEvent /* MapMouseEvent & `T$16` */
    var dblclick: MapMouseEvent /* MapMouseEvent & `T$16` */
    var mousedown: MapMouseEvent /* MapMouseEvent & `T$16` */
    var mouseup: MapMouseEvent /* MapMouseEvent & `T$16` */
    var mousemove: MapMouseEvent /* MapMouseEvent & `T$16` */
    var mouseenter: MapMouseEvent /* MapMouseEvent & `T$16` */
    var mouseleave: MapMouseEvent /* MapMouseEvent & `T$16` */
    var mouseover: MapMouseEvent /* MapMouseEvent & `T$16` */
    var mouseout: MapMouseEvent /* MapMouseEvent & `T$16` */
    var contextmenu: MapMouseEvent /* MapMouseEvent & `T$16` */
    var touchstart: MapTouchEvent /* MapTouchEvent & `T$16` */
    var touchend: MapTouchEvent /* MapTouchEvent & `T$16` */
    var touchcancel: MapTouchEvent /* MapTouchEvent & `T$16` */
}

external interface Layer {
    operator fun get(key: String): String?
    operator fun set(key: String, value: String?)
    var id: String
    var type: String /* 'fill' | 'line' | 'symbol' | 'circle' | 'fill-extrusion' | 'raster' | 'background' | 'heatmap' | 'hillshade' */
    var metadata: Any?
        get() = definedExternally
        set(value) = definedExternally
    var ref: String?
        get() = definedExternally
        set(value) = definedExternally
    var source: dynamic /* String | GeoJSONSourceRaw | VideoSourceRaw | ImageSourceRaw | CanvasSourceRaw | VectorSource | RasterSource | RasterDemSource */
        get() = definedExternally
        set(value) = definedExternally
    var minzoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var maxzoom: Number?
        get() = definedExternally
        set(value) = definedExternally
    var interactive: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var filter: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var layout: dynamic /* BackgroundLayout | FillLayout | FillExtrusionLayout | LineLayout | SymbolLayout | RasterLayout | CircleLayout | HeatmapLayout | HillshadeLayout */
        get() = definedExternally
        set(value) = definedExternally
    var paint: dynamic /* BackgroundPaint | FillPaint | FillExtrusionPaint | LinePaint | SymbolPaint | RasterPaint | CirclePaint | HeatmapPaint | HillshadePaint */
        get() = definedExternally
        set(value) = definedExternally
}

external interface CustomLayerInterface {
    var id: String
    var type: String /* 'custom' */
    var renderingMode: String /* '2d' | '3d' */
    val onRemove: ((map: Map, gl: WebGLRenderingContext) -> Unit)?
        get() = definedExternally
    val onAdd: ((map: Map, gl: WebGLRenderingContext) -> Unit)?
        get() = definedExternally
    val prerender: ((gl: WebGLRenderingContext, matrix: Array<Number>) -> Unit)?
        get() = definedExternally
    fun render(gl: WebGLRenderingContext, matrix: Array<Number>)
}

external interface StyleFunction {
    var stops: Array<Array<Any>>?
        get() = definedExternally
        set(value) = definedExternally
    var property: String?
        get() = definedExternally
        set(value) = definedExternally
    var base: Number?
        get() = definedExternally
        set(value) = definedExternally
    var type: String /* 'identity' | 'exponential' | 'interval' | 'categorical' */
    var default: Any?
        get() = definedExternally
        set(value) = definedExternally
    var colorSpace: String /* 'rgb' | 'lab' | 'hcl' */
}

external interface Layout {
    var visibility: String /* 'visible' | 'none' */
}

external interface BackgroundLayout : Layout

external interface BackgroundPaint {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface FillLayout : Layout {
    operator fun get(key: String): Number?
    operator fun set(key: String, value: Number?)
}

external interface FillPaint {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface FillExtrusionLayout : Layout

external interface FillExtrusionPaint {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface LineLayout : Layout {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface LinePaint {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface SymbolLayout : Layout {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface SymbolPaint {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface RasterLayout : Layout

external interface RasterPaint {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface CircleLayout : Layout

external interface CirclePaint {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface HeatmapLayout : Layout

external interface HeatmapPaint {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}

external interface HillshadeLayout : Layout

external interface HillshadePaint {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
}