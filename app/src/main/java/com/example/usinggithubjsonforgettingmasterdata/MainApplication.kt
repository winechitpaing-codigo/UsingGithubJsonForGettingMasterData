package com.example.usinggithubjsonforgettingmasterdata

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication  : Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            CaocConfig.Builder.create()
                .enabled(true).apply()
        } else {
            CaocConfig.Builder.create()
                .enabled(false).apply()
        }
    }
}