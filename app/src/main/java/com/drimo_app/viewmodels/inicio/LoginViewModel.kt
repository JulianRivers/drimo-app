package com.drimo_app.viewmodels.inicio

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var correo by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun onCorreoChange(newCorreo: String) {
        correo = newCorreo
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun iniciarSesion() {
        // Aquí añades la lógica de autenticación
    }
}


