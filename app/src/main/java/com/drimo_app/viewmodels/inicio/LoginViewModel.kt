package com.drimo_app.viewmodels.inicio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.drimo_app.data.repository.AuthRepository
import com.drimo_app.model.app.Routes
import com.drimo_app.model.start.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onValue(value: String, text: String) {
        when (text) {
            "correo" -> state = state.copy(correo = value)
            "password" -> state = state.copy(password = value)
        }
    }

    fun iniciarSesion(navController: NavController) {
        viewModelScope.launch {
            val email = "eq.${state.correo}"
            val password = "eq.${state.password}"
            val response = repo.login(email, password)

            if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                val user = response.body()?.firstOrNull()
                navController.navigate(Routes.Dreams.route)
            } else {
                print("F")
            }
        }
    }
}


