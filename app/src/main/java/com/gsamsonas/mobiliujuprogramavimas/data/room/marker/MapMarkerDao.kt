package com.gsamsonas.mobiliujuprogramavimas.data.room.marker

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MapMarkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mapMarkerEntity: MapMarkerEntity)

    @Query("SELECT * FROM marker_table")
    fun getMarkerListLive(): LiveData<List<MapMarkerEntity>>

    @Query("DELETE FROM marker_table WHERE title=:title")
    fun deleteMarkerByTitle(title: String)
}