package com.gsamsonas.mobiliujuprogramavimas.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsamsonas.mobiliujuprogramavimas.usecases.GetWordsCountFromStringUseCase
import kotlinx.coroutines.launch

class StaticTextAnalysisViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    getWordsCountFromStringUseCase: GetWordsCountFromStringUseCase
) : ViewModel() {

    val message = savedStateHandle.get<String>("message") ?: ""

    val wordCount = MutableLiveData<Int>()

    init {
        viewModelScope.launch {
            wordCount.value = getWordsCountFromStringUseCase(message)
        }
    }
}