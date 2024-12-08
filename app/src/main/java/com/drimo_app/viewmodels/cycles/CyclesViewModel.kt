package com.drimo_app.viewmodels.cycles

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.drimo_app.data.repository.CycleRepository
import com.drimo_app.model.app.Routes
import com.drimo_app.model.cycles.CycleState
import com.drimo_app.util.getUserCyclesCompleted
import com.drimo_app.util.getUserSleepTime
import com.drimo_app.util.saveUserCyclesCompleted
import com.drimo_app.util.saveUserSleepTime
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class CyclesViewModel @Inject constructor(
    private val cycleRepository: CycleRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    var state by mutableStateOf(CycleState())
        private set

    fun calculateCyclesNow(
        navController: NavController,
        hour: Date,
        isWakeUpTime: Boolean = false
    ) {
        if (checkUserHasSleepTime()) {
            state = state.copy(showAskSleepTime = true)
            return
        }
        val hourPlusMinutes = if (!isWakeUpTime) addMinutes(hour, state.minutesSleepTime) else hour;
        val sleepCycles = cycleRepository.calculateSleepCycles(
            hourPlusMinutes,
            isWakeUpTime,
            state.minutesSleepTime
        )
        state = state.copy(sleepCycles = sleepCycles)
        state = state.copy(hourCurrently = hourPlusMinutes)
        navController.navigate(Routes.CyclesResult.route)
    }

    fun askWakeUpTime(isWakeUpTime: Boolean) {
        state = state.copy(showAskHourSleep = true)
        state = state.copy(isWakeUpTime = isWakeUpTime)
    }

    fun saveSleepTime(navController: NavController, hour: Date) {
        saveUserSleepTime(context, state.minutesSleepTime)
        state = state.copy(showAskSleepTime = false)
        calculateCyclesNow(navController, hour)
    }

    fun calculateCyclesSleep(navController: NavController, hour: Date) {
        val hourTarget = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota")).apply {
            set(Calendar.HOUR_OF_DAY, state.hour)
            set(Calendar.MINUTE, state.minutes)
        }.time
        state = state.copy(showAskHourSleep = false)
        calculateCyclesNow(navController, hourTarget, state.isWakeUpTime)
    }

    fun sleepASleepCycle(navController: NavController, cycles: Int) {
        val cyclesCompleted = getUserCyclesCompleted(context)
        saveUserCyclesCompleted(context, cyclesCompleted + cycles)
        navController.navigate(Routes.Patterns.route)
    }

    fun closeModalAskSleepTime() {
        state = state.copy(showAskSleepTime = false)
    }

    fun closeModalAskHourSleep() {
        state = state.copy(showAskHourSleep = false)
    }

    fun onValue(value: Int, text: String) {
        when (text) {
            "hour" -> state = state.copy(hour = value)
            "minutes" -> state = state.copy(minutes = value)
            "minutesSleepTime" -> state = state.copy(minutesSleepTime = value)
        }
    }

    private fun addMinutes(date: Date, minutes: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MINUTE, minutes)
        return calendar.time
    }

    private fun checkUserHasSleepTime(): Boolean {
        val userSleepTime = getUserSleepTime(context)
        return userSleepTime == -1
    }
}