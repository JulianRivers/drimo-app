package com.drimo_app.viewmodels.dreams

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drimo_app.data.repository.DreamRepository
import com.drimo_app.model.dreams.UpdateDreamRequest
import com.drimo_app.model.dreams.UpdateDreamState
import com.drimo_app.util.clearUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateDreamViewModel @Inject constructor(private val repo: DreamRepository, @ApplicationContext private val context: Context): ViewModel() {

    var state by mutableStateOf(UpdateDreamState())
        private set


    // Estados para el MessageDialog
    var showDialog by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)
    var message by mutableStateOf("")

    fun loadDream(id: Int) {
        viewModelScope.launch {
            try {
                val response = repo.getDreamById(id)
                if (response.isSuccessful) {
                    val dreams = response.body()
                    if (!dreams.isNullOrEmpty()) {
                        val dream = dreams[0]
                        state = state.copy(
                            id = dream.id,
                            title = dream.title,
                            description = dream.description,
                            tags = dream.tags,
                            sleepFactors = dream.sleepFactors
                        )
                    } else {
                        // Manejo de lista vacía
                        state = state.copy(
                            id = 0,
                            title = "No encontrado",
                            description = "No se encontró un sueño con el ID especificado.",
                            tags = listOf(),
                            sleepFactors = listOf()
                        )
                    }
                } else {
                    // Manejo de respuesta no exitosa
                    state = state.copy(
                        id = 0,
                        title = "Error",
                        description = "Error al cargar el sueño: ${response.errorBody()?.string()}",
                        tags = listOf(),
                        sleepFactors = listOf()
                    )
                }
            } catch (e: Exception) {
                // Manejo de excepciones
                state = state.copy(
                    id = 0,
                    title = "Error",
                    description = "Ocurrió un error: ${e.message}",
                    tags = listOf("Error"),
                    sleepFactors = listOf("Indefinido")
                )
            }
        }
    }


    fun onValue(value: Any, text: String) {
        when (text) {
            "title" -> {
                if (value is String) {
                    state = state.copy(title = value.replaceFirstChar { it.uppercaseChar() })
                }
            }
            "description" -> {
                if (value is String) {
                    state = state.copy(description = value.replaceFirstChar { it.uppercaseChar() })
                }
            }
            "tags" -> {
                if (value is List<*>) {
                    state = state.copy(tags = value.filterIsInstance<String>())
                }
            }
            "sleepFactors" -> {
                if (value is List<*>) {
                    state = state.copy(sleepFactors = value.filterIsInstance<String>())
                }
            }
        }
    }

    fun updateDream() {
        viewModelScope.launch {
            val updatedDream = UpdateDreamRequest(
                title = state.title,
                description = state.description,
                tags = state.tags,
                sleepFactors = state.sleepFactors
            )

            val response = repo.updateDream(state.id, updatedDream)

            // Handle the response
            if (response.isSuccessful) {
                showDialog = true
                isSuccess = true
                message = "Sueño actualizado exitosamente"
            } else {
                showDialog = true
                isSuccess = false
                message = "Ocurrio un error"
            }
        }
    }

    fun deleteDream() {
        viewModelScope.launch {
            val response = repo.deleteById(state.id)
            if (response.isSuccessful) {
                showDialog = true
                isSuccess = true
                message = "Sueño eliminado exitosamente"
            } else {
                showDialog = true
                isSuccess = false
                message = "Ocurrio un error"
            }
       }
    }

    fun logout() {
        viewModelScope.launch {
            clearUserId(context)
        }
    }
}