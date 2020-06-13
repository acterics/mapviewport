package com.oleglipskiy.mapviewport.sample.extensions

inline fun jsObject(init: dynamic.() -> Unit): dynamic {
    val o = js("{}")
    init(o)
    return o
}
inline fun <T>jsObject(init: dynamic.() -> Unit): T {
    val o = js("{}")
    init(o)
    return o as T
}

inline fun append(receiver: dynamic, init: dynamic.() -> Unit): dynamic {
    init(receiver)
    return receiver
}