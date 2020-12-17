package com.gsamsonas.mobiliujuprogramavimas.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gsamsonas.mobiliujuprogramavimas.data.room.marker.MapMarkerDao
import com.gsamsonas.mobiliujuprogramavimas.data.room.marker.MapMarkerEntity
import com.gsamsonas.mobiliujuprogramavimas.models.MapMarker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapRepository @Inject constructor(
    private val mapMarkerDao: MapMarkerDao
) {

    fun getMarkerList(): LiveData<List<MapMarker>> {
        return Transformations.map(mapMarkerDao.getMarkerListLive()) { entityList ->
            entityList.map { MapMarker(it.title, it.latitude, it.longitude) }
        }
    }

    suspend fun addMarker(marker: MapMarker) {
        withContext(Dispatchers.IO) {
            mapMarkerDao.insert(MapMarkerEntity(marker.title, marker.latitude, marker.longitude))
        }
    }

    suspend fun deleteMarker(title: String) {
        withContext(Dispatchers.IO) {
            mapMarkerDao.deleteMarkerByTitle(title)
        }
    }
}