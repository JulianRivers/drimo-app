package com.drimo_app.viewmodels.dreams

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drimo_app.data.repository.DreamRepository
import com.drimo_app.model.dreams.Dream
import com.drimo_app.util.getUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamViewModel @Inject constructor(private val repository: DreamRepository, @ApplicationContext private val context: Context) : ViewModel() {
    private val _dreams = MutableStateFlow<List<Dream>>(emptyList())
    val dreams: StateFlow<List<Dream>> get() = _dreams

    init {
        fetchDreams()
    }

    private fun fetchDreams() {
        viewModelScope.launch {
            val userId = getUserId(context) ?: -1
            val response = repository.getDreams(userId)
            if (response.isSuccessful) {
                _dreams.value = response.body() ?: emptyList()
            } else {
                print("F")
            }
        }
    }
}
