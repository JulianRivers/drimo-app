package com.drimo_app.viewmodels.patterns

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drimo_app.data.repository.DreamRepository
import com.drimo_app.model.patterns.Factor
import com.drimo_app.model.patterns.SleepPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatternsViewModel @Inject constructor(private val repo: DreamRepository): ViewModel() {
    var sleepPattern = mutableStateOf(SleepPattern(emptyList(), emptyList(), 0, 0))
        private set

    fun loadStatistics() {
        viewModelScope.launch {
            try {
                val response = repo.getDreamStatistics()
                if (response.isSuccessful) {
                    val dreams = response.body() ?: emptyList()

                    // Procesa los datos para obtener estadísticas
                    val factorsCount = mutableMapOf<String, Int>()
                    val tagsCount = mutableMapOf<String, Int>()

                    dreams.forEach { dream ->
                        dream.sleepFactors.forEach { factor ->
                            factorsCount[factor] = factorsCount.getOrDefault(factor, 0) + 1
                        }
                        dream.tags.forEach { tag ->
                            tagsCount[tag] = tagsCount.getOrDefault(tag, 0) + 1
                        }
                    }

                    val factors = factorsCount.map { Factor(it.key, it.value) }
                    val tags = tagsCount.keys.toList()

                    // Actualiza el estado con los datos procesados
                    sleepPattern.value = SleepPattern(
                        factors = factors,
                        tags = tags,
                        completedCycles = dreams.size,
                        plannedHours = dreams.sumOf { it.sleepFactors.size } // Ejemplo de suma
                    )
                }
            } catch (e: Exception) {
                // Manejo de errores
                println("Error loading statistics: ${e.message}")
            }
        }
    }
}