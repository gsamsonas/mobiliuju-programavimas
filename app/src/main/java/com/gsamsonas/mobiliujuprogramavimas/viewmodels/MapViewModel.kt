package com.gsamsonas.mobiliujuprogramavimas.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsamsonas.mobiliujuprogramavimas.data.MapRepository
import com.gsamsonas.mobiliujuprogramavimas.models.MapMarker
import kotlinx.coroutines.launch

class MapViewModel @ViewModelInject constructor(
    private val mapRepository: MapRepository
) : ViewModel() {

    val title = MutableLiveData<String>()
    val latitude = MutableLiveData<String>()
    val longitude = MutableLiveData<String>()

    val markerList = mapRepository.getMarkerList()

    fun save(){
        val title = title.value ?: return
        if (title.isBlank()) return

        val latitudeString = latitude.value ?: return
        if (latitudeString.isBlank()) return

        val longitudeString = longitude.value ?: return
        if (longitudeString.isBlank()) return

        viewModelScope.launch {
            mapRepository.addMarker(MapMarker(
                title,
                latitudeString.toDouble(),
                longitudeString.toDouble()
            ))
        }
    }
}