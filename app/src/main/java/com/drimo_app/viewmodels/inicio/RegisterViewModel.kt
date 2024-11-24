package com.drimo_app.viewmodels.inicio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.drimo_app.data.repository.UserRepository
import com.drimo_app.model.app.Routes
import com.drimo_app.model.start.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: UserRepository) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    // Estados para el MessageDialog
    var showDialog by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)
    var message by mutableStateOf("")

    fun onValue(value: String, text: String) {
        when (text) {
            "email" -> state = state.copy(email = value)
            "password" -> state = state.copy(password = value)
            "confirmPassword" -> state = state.copy(confirmPassword = value)
        }
    }

    fun registerAccount(navController: NavController) {
        viewModelScope.launch {
            val email = state.email
            val password = state.password
            val confirmPassword = state.confirmPassword

            // Validaciones
            // Validaciones
            if (email.isBlank()) {
                message = "El campo de correo electrónico no puede estar vacío"
                isSuccess = false
                showDialog = true
                return@launch
            }

            if (password.isBlank() || confirmPassword.isBlank()) {
                message = "Los campos de contraseña no pueden estar vacíos"
                isSuccess = false
                showDialog = true
                return@launch
            }

            if (password != confirmPassword) {
                message = "Las contraseñas no coinciden"
                isSuccess = false
                showDialog = true
                return@launch
            }

            // Peticion
            val response = repo.register(email, password)

            if (response.isSuccessful) {
                message = "¡Registro exitoso!"
                isSuccess = true
                showDialog = true
            } else {
                message = "Error en el registro, el correo ya se encuentra reigstrado"
                isSuccess = false
                showDialog = true
            }
        }
    }
}