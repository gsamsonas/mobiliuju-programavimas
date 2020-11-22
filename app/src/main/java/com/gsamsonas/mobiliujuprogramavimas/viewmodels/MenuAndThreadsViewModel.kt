package com.gsamsonas.mobiliujuprogramavimas.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MenuAndThreadsViewModel @ViewModelInject constructor() : ViewModel() {

    private val timeDifference = MutableLiveData<Int>()
    val timeDifferenceText: LiveData<String> = Transformations.map(timeDifference) {
        "Laiko skirtumas yra $it minutės"
    }

    private val textLength = MutableLiveData<Int>()
    val textLengthText: LiveData<String> = Transformations.map(textLength) {
        "Tekste yra $it simboliai"
    }

    private val _letter = MutableLiveData<Char>()
    val letter: LiveData<String> = Transformations.map(_letter) { it.toString() }

}