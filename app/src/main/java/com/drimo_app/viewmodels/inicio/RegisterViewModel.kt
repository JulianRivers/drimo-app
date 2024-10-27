package com.drimo_app.viewmodels.inicio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.drimo_app.model.start.RegisterState

class RegisterViewModel: ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    fun onValue(value: String, text: String) {
        when (text) {
            "correo" -> state = state.copy(correo = value)
            "password" -> state = state.copy(password = value)
            "confirmPassword" -> state = state.copy(confirmPassword = value)
        }
    }

    fun registrarCuenta() {
    }
}