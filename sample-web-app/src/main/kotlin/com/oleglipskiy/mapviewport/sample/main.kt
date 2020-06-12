package com.oleglipskiy.mapviewport.sample

import com.oleglipskiy.mapviewport.sample.app.Application
import com.oleglipskiy.mapviewport.sample.app.MainApplication
import com.oleglipskiy.mapviewport.sample.app.module
import kotlin.browser.document
import kotlin.dom.hasClass

fun main() {
    var application: Application? = null

    val state: dynamic = module.hot?.let { hot ->
        hot.accept()
        hot.dispose { data ->
            data.appState = application?.dispose()
            application = null
        }

        hot.data
    }

    if (document.body != null) {
        application = start(state)
    } else {
        application = null
        document.addEventListener("DOMContentLoaded", {
            console.log("DOMContentLoaded")
            application = start(state)
        })
    }
}

fun start(state: dynamic): Application? {
    return if (document.body?.hasClass("app") == true) {
        val application = MainApplication()

        @Suppress("UnsafeCastFromDynamic")
        application.start(state?.appState ?: emptyMap())

        application
    } else {
        null
    }
}
