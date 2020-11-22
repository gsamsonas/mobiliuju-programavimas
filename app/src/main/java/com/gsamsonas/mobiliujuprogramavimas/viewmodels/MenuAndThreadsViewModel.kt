package com.gsamsonas.mobiliujuprogramavimas.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gsamsonas.mobiliujuprogramavimas.usecases.TimerUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class MenuAndThreadsViewModel @ViewModelInject constructor(
    private val timerUseCase: TimerUseCase<Char>
) : ViewModel() {

    private val timeDifference = MutableLiveData<Long>()
    val timeDifferenceText: LiveData<String> = Transformations.map(timeDifference) {
        "Laiko skirtumas yra $it minutės"
    }

    private val textLength = MutableLiveData<Int>()
    val textLengthText: LiveData<String> = Transformations.map(textLength) {
        "Tekste yra $it simboliai"
    }

    private val _letter = MutableLiveData<Char>()
    val letter: LiveData<String> = Transformations.map(_letter) { it.toString() }

    init {
        viewModelScope.launch {
            timerUseCase.getFlow().collect {
                _letter.value = it
            }
        }
    }

    fun onTimeSelected(time: LocalTime) {
        val minutes = time.until(LocalTime.now(), ChronoUnit.MINUTES)
        timeDifference.value = minutes
    }

    fun onCalculateLength(text: String) {
        textLength.value = text.length
    }

    fun onOutputLetter(text: String) {
        timerUseCase.stop()
        var iterator = 0
        timerUseCase.start(100) {
            iterator ++
            if (iterator > text.length) {
                timerUseCase.stop()
                ' '
            } else {
                text[iterator - 1]
            }
        }
    }
}