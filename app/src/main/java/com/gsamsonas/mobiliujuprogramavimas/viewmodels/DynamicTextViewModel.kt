package com.gsamsonas.mobiliujuprogramavimas.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gsamsonas.mobiliujuprogramavimas.usecases.GetWordsCountFromStringUseCase
import kotlinx.coroutines.launch

class DynamicTextViewModel @ViewModelInject constructor(
    getWordsCountFromStringUseCase: GetWordsCountFromStringUseCase
) : ViewModel() {

    val message = MutableLiveData<String>()
    val externalMessage = MutableLiveData<String>()

    val wordCount: LiveData<Int> = MediatorLiveData<Int>().apply {
        addSource(message) {
            viewModelScope.launch {
                value = if (it.isNullOrBlank()) 0 else getWordsCountFromStringUseCase(it)
            }
        }
    }

    fun onReceivedExternalMessage(message: String?) {
        externalMessage.value = message
    }

    fun getExternalMessage() = externalMessage.value ?: ""
}