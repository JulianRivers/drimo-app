package com.drimo_app.viewmodels.blog

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(@ApplicationContext private val context: Context): ViewModel() {

    fun logout() {
        viewModelScope.launch {
            com.drimo_app.util.clearUserId(context)
        }
    }
}