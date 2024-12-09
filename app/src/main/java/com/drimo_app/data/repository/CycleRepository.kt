package com.drimo_app.data.repository

import java.util.Calendar
import java.util.Date


class CycleRepository {
    fun calculateSleepCycles(
        hour: Date,
        isWakeUpTime: Boolean = false,
        minutesSleepTime: Int
    ): List<Date> {

        val calendar = Calendar.getInstance()
        calendar.time = hour

        val sleepCycles = mutableListOf<Date>()
        val numberOfCycles = 6

        for (i in 1..numberOfCycles) {
            val cycleRest = if (i == 1) -(90+minutesSleepTime) else (-90);
            if (isWakeUpTime) calendar.add(Calendar.MINUTE, cycleRest)
            else calendar.add(Calendar.MINUTE, 90)

            sleepCycles.add(calendar.time)
        }

        return sleepCycles
    }
}