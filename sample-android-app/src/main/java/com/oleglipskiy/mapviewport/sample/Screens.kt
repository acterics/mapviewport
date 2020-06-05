package com.oleglipskiy.mapviewport.sample

import androidx.fragment.app.Fragment
import com.oleglipskiy.mapviewport.sample.ui.map.MapFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object Map: SupportAppScreen() {
        override fun getFragment() = MapFragment()
    }
}