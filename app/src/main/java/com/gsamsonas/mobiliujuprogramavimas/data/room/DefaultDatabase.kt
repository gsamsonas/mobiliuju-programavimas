package com.gsamsonas.mobiliujuprogramavimas.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gsamsonas.mobiliujuprogramavimas.data.room.marker.MapMarkerDao
import com.gsamsonas.mobiliujuprogramavimas.data.room.marker.MapMarkerEntity


@Database(
    version = 1,
    exportSchema = false,
    entities = [
        MapMarkerEntity::class
    ]
)
abstract class DefaultDatabase : RoomDatabase() {

    abstract fun mapMarkerDao(): MapMarkerDao
}