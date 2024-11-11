package com.drimo_app.viewmodels.dreams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drimo_app.data.repository.DreamRepository
import com.drimo_app.model.dreams.Dream
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamViewModel @Inject constructor(private val repository: DreamRepository) : ViewModel() {
    private val _dreams = MutableStateFlow<List<Dream>>(emptyList())
    val dreams: StateFlow<List<Dream>> get() = _dreams

    init {
        fetchDreams()
    }

    private fun fetchDreams() {
        viewModelScope.launch {
            val response = repository.getDreams()
            if (response.isSuccessful) {
                _dreams.value = response.body() ?: emptyList()
            } else {
                print("F")
            }
        }
    }
}
