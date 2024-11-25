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
import com.drimo_app.util.clearUserSleepTime
import com.drimo_app.util.getUserSleepTime
import com.drimo_app.util.saveUserSleepTime
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import java.util.Date
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
        val hourPlusMinutes = addMinutes(hour, state.minutesSleepTime)
        val sleepCycles = cycleRepository.calculateSleepCycles(hourPlusMinutes, isWakeUpTime)
        state = state.copy(sleepCycles = sleepCycles)
        state = state.copy(hourCurrently = hourPlusMinutes)
        navController.navigate(Routes.CyclesResult.route)
    }

    fun askWakeUpTime() {
        state = state.copy(showAskHourSleep = true)
    }

    fun askBedTime() {

    }

    fun saveSleepTime(navController: NavController, hour: Date) {
        saveUserSleepTime(context, state.minutesSleepTime)
        state = state.copy(showAskSleepTime = false)
        calculateCyclesNow(navController, hour)
    }

    fun calculateCyclesSleep(navController: NavController, hour: Date) {
        saveUserSleepTime(context, state.minutesSleepTime)
        state = state.copy(showAskSleepTime = false)
        calculateCyclesNow(navController, hour)
    }

    fun closeModalAskSleepTime() {
        state = state.copy(showAskSleepTime = false)
    }

    fun closeModalAskHourSleep() {
        state = state.copy(showAskHourSleep = false)
    }

    fun onValueMinutesSleepTime(value: Int) {
        state = state.copy(minutesSleepTime = value)
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