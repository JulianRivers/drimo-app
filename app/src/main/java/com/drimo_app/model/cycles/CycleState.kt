package com.drimo_app.model.cycles

import java.util.Date

data class CycleState (

    val hourCurrently: Date = Date(),
    val selectedHour: Date = Date(),
    val minutesSleepTime: Int = 10,
    val showAskSleepTime: Boolean = false,
    val showAskHourSleep: Boolean = false,
    val sleepCycles: List<Date> = emptyList()
)