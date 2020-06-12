package com.oleglipskiy.mapviewport.sample.app

abstract class Application {
    abstract val stateKeys: List<String>

    abstract fun start(state: Map<String, Any>)
    abstract fun dispose(): Map<String, Any>
}