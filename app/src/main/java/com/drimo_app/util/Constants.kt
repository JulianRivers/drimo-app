package com.drimo_app.util

import com.drimo_app.BuildConfig
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class Constants {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        const val apiKey = BuildConfig.API_KEY
        val timeFormat = SimpleDateFormat("hh:mm a", Locale("es", "CO")).apply {
            timeZone = TimeZone.getTimeZone("America/Bogota")
        }
        val dateFormat =
            SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", Locale("es", "CO")).apply {
                timeZone = TimeZone.getTimeZone("America/Bogota")
            }
    }
}
