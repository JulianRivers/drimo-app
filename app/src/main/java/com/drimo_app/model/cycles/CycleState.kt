package com.drimo_app.model.cycles

import java.util.Date

data class CycleState (
    val hourCurrently: Date = Date(),
    val sleepCycles: List<Date> = emptyList()
)