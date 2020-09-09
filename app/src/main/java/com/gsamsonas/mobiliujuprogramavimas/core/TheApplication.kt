package com.gsamsonas.mobiliujuprogramavimas.core

import android.app.Application
import com.gsamsonas.mobiliujuprogramavimas.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TheApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}