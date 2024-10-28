package com.drimo_app.viewmodels.inicio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.drimo_app.model.start.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    fun onValue(value: String, text: String) {
        when (text) {
            "email" -> state = state.copy(email = value)
            "password" -> state = state.copy(password = value)
            "confirmPassword" -> state = state.copy(confirmPassword = value)
        }
    }

    fun registerAccount() {
    }
}