package com.gsamsonas.mobiliujuprogramavimas.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gsamsonas.mobiliujuprogramavimas.core.Event
import com.gsamsonas.mobiliujuprogramavimas.models.GraphicalInformationModel
import java.time.LocalTime

class GraphicalInformationInputViewModel @ViewModelInject constructor() : ViewModel() {
    val title = MutableLiveData<String>()
    val faculty = MutableLiveData<String>()
    val difficulty = MutableLiveData<Float>().apply { value = 0f }
    val day = MutableLiveData<String>()
    val time = MutableLiveData<LocalTime>()
    val register = MutableLiveData<Boolean>().apply { value = false }

    val onError = MutableLiveData<Event<GraphicalInformationInputError>>()

    val onSave = MutableLiveData<Event<GraphicalInformationModel>>()

    fun onDaySelected(day: String) {
        this.day.value = day
    }

    fun onTimeSelected(localTime: LocalTime) {
        time.value = localTime
    }

    fun onSave() {
        val model = validateFields() ?: return
        onSave.value = Event(model)
    }

    private fun validateFields(): GraphicalInformationModel? {
        val title = if (title.value.isNullOrBlank()) {
            onError.value = Event(GraphicalInformationInputError.MissingTitle)
            return null
        } else {
            title.value!!
        }
        val faculty = if (faculty.value.isNullOrBlank()) {
            onError.value = Event(GraphicalInformationInputError.MissingFaculty)
            return null
        } else {
            faculty.value!!
        }
        val difficulty = difficulty.value ?: return null
        val day = if (day.value.isNullOrBlank()) {
            onError.value = Event(GraphicalInformationInputError.MissingDay)
            return null
        } else {
            day.value!!
        }
        val time = if (time.value == null) {
            onError.value = Event(GraphicalInformationInputError.MissingTime)
            return null
        } else {
            time.value!!
        }
        val registered = register.value ?: return null

        return GraphicalInformationModel(title, faculty, difficulty, day, time, registered)
    }

    sealed class GraphicalInformationInputError {
        object MissingTitle : GraphicalInformationInputError()
        object MissingFaculty : GraphicalInformationInputError()
        object MissingDay : GraphicalInformationInputError()
        object MissingTime : GraphicalInformationInputError()
    }
}