package com.gsamsonas.mobiliujuprogramavimas.data.room.marker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "marker_table")
data class MapMarkerEntity (
    @PrimaryKey
    val title: String,
    val latitude: Double,
    val longitude: Double
)