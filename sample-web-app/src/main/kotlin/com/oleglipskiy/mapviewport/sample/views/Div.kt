package com.oleglipskiy.mapviewport.sample.views

import org.w3c.dom.HTMLElement
import org.w3c.dom.get
import kotlin.dom.appendElement

class Div(parent: HTMLElement, val name: String) {

    val element: HTMLElement

    init {
        parent.appendElement("div") {
            className = name
        }

        element = parent.getElementsByClassName(name)[0] as HTMLElement
    }

}