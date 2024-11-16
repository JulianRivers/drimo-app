package com.drimo_app.util

import android.content.Context

// Guardar el ID del usuario
fun saveInfoUser(context: Context, id: Int) {
    val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putInt("Id", id)
        apply()
    }
}

// Obtener el ID del usuario
fun getInfoUser(context: Context): Int? {
    val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    return if (sharedPref.contains("Id")) {
        sharedPref.getInt("Id", -1)
    } else {
        null
    }
}

// Eliminar el ID del usuario
fun clearInfoUser(context: Context) {
    val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        remove("Id")
        apply()
    }
}
