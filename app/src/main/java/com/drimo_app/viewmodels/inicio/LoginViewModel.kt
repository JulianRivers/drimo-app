package com.drimo_app.viewmodels.inicio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.drimo_app.model.start.LoginState

class LoginViewModel : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onValue(value: String, text: String) {
        when (text) {
            "correo" -> state = state.copy(correo = value)
            "password" -> state = state.copy(password = value)
        }
    }

    fun iniciarSesion(navController: NavController) {
        navController.navigate("Register")
    }
}


