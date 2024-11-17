package com.drimo_app.viewmodels.cycles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.drimo_app.data.repository.CycleRepository
import com.drimo_app.model.app.Routes
import com.drimo_app.model.cycles.CycleState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CyclesViewModel @Inject constructor(
    private val cycleRepository: CycleRepository
): ViewModel() {
    var state by mutableStateOf(CycleState())
        private set

    fun calculateCyclesNow(
        navController: NavController,
        hour: Date,
        isWakeUpTime: Boolean = false
    ) {
        val sleepCycles = cycleRepository.calculateSleepCycles(hour, isWakeUpTime)
        state = state.copy(sleepCycles = sleepCycles)
        navController.navigate(Routes.CyclesResult.route)
    }

    fun askWakeUpTime() {

    }

    fun askBedTime() {

    }
}