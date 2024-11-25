package com.drimo_app.model.cycles

import java.util.Date

data class CycleState (
    val hourCurrently: Date = Date(),
    val hour: Int = 10,
    val minutes: Int = 30,
    val selectedHour: Date = Date(),
    val minutesSleepTime: Int = 10,
    val isWakeUpTime: Boolean = false,
    val showAskSleepTime: Boolean = false,
    val showAskHourSleep: Boolean = false,
    val sleepCycles: List<Date> = emptyList()
)