package com.gsamsonas.mobiliujuprogramavimas.models

import java.time.LocalTime

data class GraphicalInformationModel (
    val title: String,
    val faculty: String,
    val difficulty: Float,
    val dayOfWeek: String,
    val time: LocalTime,
    val registered: Boolean
)