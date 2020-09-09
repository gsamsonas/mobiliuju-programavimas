package com.gsamsonas.mobiliujuprogramavimas.core

import androidx.lifecycle.Observer

class EventObserver<T> (private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(t: Event<T>?) {
        t?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}