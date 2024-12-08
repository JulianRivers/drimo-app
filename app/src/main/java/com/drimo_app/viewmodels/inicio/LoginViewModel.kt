package com.drimo_app.viewmodels.inicio

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.drimo_app.data.repository.UserRepository
import com.drimo_app.model.app.Routes
import com.drimo_app.model.start.LoginState
import com.drimo_app.util.saveUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: UserRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
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
            val email = "eq.${state.correo.trim()}"
            val password = "eq.${state.password.trim()}"
            val response = repo.login(email, password)

            if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                val user = response.body()?.firstOrNull()
                user?.let {
                    saveUserId(context, it.id)
                    navController.navigate(Routes.Dreams.route)
                }
            } else {
                print("F")
            }
        }
    }

    fun guestLogin(navController: NavController) {
        val guestUser = UUID.randomUUID().toString()
        viewModelScope.launch {
            val responseRegister = repo.register(guestUser, guestUser)
            val responseLogin = repo.login("eq.$guestUser", "eq.$guestUser")
            if (responseLogin.isSuccessful && responseLogin.body()?.isNotEmpty() == true) {
                val user = responseLogin.body()?.firstOrNull()
                user?.let {
                    saveUserId(context, it.id)
                }
            } else {
                print("F")
            }
            navController.navigate(Routes.Dreams.route)
        }

    }
}


