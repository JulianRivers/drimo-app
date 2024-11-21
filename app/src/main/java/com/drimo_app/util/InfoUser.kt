package com.drimo_app.util

import android.content.Context

// Guardar el ID del usuario
fun saveUserId(context: Context, id: Int) {
    val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putInt("Id", id)
        apply()
    }
}

// Obtener el ID del usuario
fun getUserId(context: Context): Int? {
    val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    return if (sharedPref.contains("Id")) {
        sharedPref.getInt("Id", -1)
    } else {
        null
    }
}

// Eliminar el ID del usuario
fun clearUserId(context: Context) {
    val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        remove("Id")
        apply()
    }
}

fun saveUserSleepTime(context: Context, valor: Int) {
    val sharedPreferences = context.getSharedPreferences("user_sleep_time", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putInt("sleep_duration", valor)
        apply()
    }
}

fun getUserSleepTime(context: Context): Int {
    val sharedPreferences = context.getSharedPreferences("user_sleep_time", Context.MODE_PRIVATE)
    return sharedPreferences.getInt("sleep_duration", 0)
}

fun clearUserSleepTime(context: Context) {
    val sharedPreferences = context.getSharedPreferences("user_sleep_time", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        remove("sleep_duration")
        apply()
    }
}
