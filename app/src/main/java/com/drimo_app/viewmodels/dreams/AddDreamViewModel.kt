package com.drimo_app.viewmodels.dreams

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drimo_app.data.repository.DreamRepository
import com.drimo_app.model.dreams.AddDreamState
import com.drimo_app.util.getInfoUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDreamViewModel @Inject constructor(private val repo: DreamRepository, @ApplicationContext private val context: Context): ViewModel() {
    var state by mutableStateOf(AddDreamState())
        private set


    // Estados para el MessageDialog
    var showDialog by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)
    var message by mutableStateOf("")

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

    fun addDream() {
        viewModelScope.launch {

            // Validar que ningún campo esté vacío o sea null
            if (state.title.isBlank()) {
                message = "El título no puede estar vacío"
                isSuccess = false
                showDialog = true
                return@launch
            }

            if (state.description.isBlank()) {
                message = "La descripción no puede estar vacía"
                isSuccess = false
                showDialog = true
                return@launch
            }

            if (state.tags.isEmpty()) {
                message = "Debe agregar al menos una etiqueta"
                isSuccess = false
                showDialog = true
                return@launch
            }

            if (state.sleepFactors.isEmpty()) {
                message = "Debe seleccionar al menos un factor de sueño"
                isSuccess = false
                showDialog = true
                return@launch
            }
            val userId = getInfoUser(context) ?: -1

            // Llamar al repositorio para agregar el sueño
            val response = repo.addDream(
                title = state.title,
                description = state.description,
                tags = state.tags,
                sleepFactors = state.sleepFactors,
                user_id = userId
            )

            // Verificar si la operación fue exitosa
            if (response.isSuccessful) {
                message = "¡sueño registrado exitosamente!"
                isSuccess = true
                showDialog = true

                clearFields()
            } else {
                message = "Error al registrar sueño"
                isSuccess = false
                showDialog = true
            }
        }
    }

    private fun clearFields() {
        state = state.copy(
            title = "",
            description = "",
            tags = emptyList(),
            sleepFactors = emptyList(),
        )
    }
}