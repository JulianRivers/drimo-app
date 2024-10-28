package com.drimo_app.viewmodels.dreams

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.drimo_app.model.dreams.AddDreamState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDreamViewModel @Inject constructor(): ViewModel() {
    var state by mutableStateOf(AddDreamState())
        private set

    fun onValue(value: Any, text: String) {
        when (text) {
            "title" -> if (value is String) state = state.copy(title = value)
            "description" -> if (value is String) state = state.copy(description = value)
            "tags" -> if (value is List<*>) state = state.copy(tags = value.filterIsInstance<String>())
            "sleepFactors" -> if (value is List<*>) state = state.copy(sleepFactors = value.filterIsInstance<String>())
        }
    }

    fun addDream() {
    }
}