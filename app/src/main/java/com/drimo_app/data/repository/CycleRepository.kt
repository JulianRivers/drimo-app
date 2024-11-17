package com.drimo_app.data.repository

import java.util.Calendar
import java.util.Date


class CycleRepository {
    fun calculateSleepCycles(hour: Date, isWakeUpTime: Boolean = false): List<Date> {
        val calendar = Calendar.getInstance()
        calendar.time = hour

        val sleepCycles = mutableListOf<Date>()
        val numberOfCycles = 6

        for (i in 1..numberOfCycles) {
            if (isWakeUpTime) calendar.add(Calendar.MINUTE, -90)
            else calendar.add(Calendar.MINUTE, 90)

            sleepCycles.add(calendar.time)
        }

        if (isWakeUpTime) sleepCycles.reverse()

        return sleepCycles
    }
}