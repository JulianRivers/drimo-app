package com.drimo_app.viewmodels.inicio

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.drimo_app.util.getUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor( @ApplicationContext private val context: Context) : ViewModel()  {

    var isLogged by mutableStateOf(false)
        private set

    init {
        cargarInfoUser()
    }

    private fun cargarInfoUser() {
        val userId = getUserId(context) ?: -1
        isLogged = userId != -1
    }
}