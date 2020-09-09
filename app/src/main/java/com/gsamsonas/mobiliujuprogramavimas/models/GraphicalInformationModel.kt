package com.gsamsonas.mobiliujuprogramavimas.models

import java.time.LocalTime

data class GraphicalInformationModel (
    val title: String,
    val faculty: String,
    val difficulty: Float,
    val dayOfWeek: String,
    val time: LocalTime,
    val registered: Boolean
) {
    override fun toString(): String {
        return """Title: $title
            |Faculty: $faculty
            |Difficulty: $difficulty
            |Day: $dayOfWeek
            |Time: $time
            |Registered: $registered
        """.trimMargin()
    }
}