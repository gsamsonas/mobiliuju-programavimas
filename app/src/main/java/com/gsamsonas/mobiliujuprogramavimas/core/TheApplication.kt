package com.gsamsonas.mobiliujuprogramavimas.core

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.gsamsonas.mobiliujuprogramavimas.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

const val BATTERY_NOTIFICATION_CHANNEL = "BATTERY_NOTIFICATION_CHANNEL"

@HiltAndroidApp
class TheApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        val service = getSystemService(NOTIFICATION_SERVICE) as? NotificationManager
        val notificationChannel = NotificationChannel("BATTERY_NOTIFICATION_CHANNEL", "Battery", NotificationManager.IMPORTANCE_HIGH)
        service?.createNotificationChannel(notificationChannel)
    }
}