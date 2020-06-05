package com.oleglipskiy.mapviewport.sample

import android.app.Application
import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier
import com.oleglipskiy.mapviewport.sample.di.DI
import com.oleglipskiy.mapviewport.sample.di.module.AppModule
import com.oleglipskiy.mapviewport.BuildConfig
import toothpick.Toothpick
import toothpick.configuration.Configuration

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        initToothpick()
        initAppScope()
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().preventMultipleRootScopes())
        }
    }

    private fun initAppScope() {
        Toothpick.openScope(DI.APP_SCOPE)
            .installModules(
                AppModule(this)
            )
    }

}