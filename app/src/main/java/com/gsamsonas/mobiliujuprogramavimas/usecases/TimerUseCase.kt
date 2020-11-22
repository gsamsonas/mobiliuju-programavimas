package com.gsamsonas.mobiliujuprogramavimas.usecases

import android.os.Handler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TimerUseCase<T> @Inject constructor () {

    private val timeChannel = ConflatedBroadcastChannel<T>()
    private val timerThreadHandler = Handler()
    private var updateTimerThreadRunnable: Runnable? = null

    fun start(delayMillis: Long, action: () -> T) {
        updateTimerThreadRunnable = object : Runnable {
            override fun run() {
                CoroutineScope(Dispatchers.Default).launch {
                    timeChannel.send(action())
                }
                timerThreadHandler.postDelayed(this, delayMillis)
            }
        }
        timerThreadHandler.postDelayed(updateTimerThreadRunnable!!, delayMillis)
    }

    fun stop() {
        updateTimerThreadRunnable?.let { timerThreadHandler.removeCallbacks(it) }
    }

    fun getFlow(): Flow<T> {
        return timeChannel.asFlow()
    }
}