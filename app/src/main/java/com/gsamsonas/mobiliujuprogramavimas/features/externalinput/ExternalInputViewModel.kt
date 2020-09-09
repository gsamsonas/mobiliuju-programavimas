package com.gsamsonas.mobiliujuprogramavimas.features.externalinput

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gsamsonas.mobiliujuprogramavimas.core.Event

class ExternalInputViewModel @ViewModelInject constructor() : ViewModel() {

    val input = MutableLiveData<String>()

    val onSaveInput = MutableLiveData<Event<String>>()

    fun onSaveInput() {
        onSaveInput.value = Event(input.value ?: "")
    }
}