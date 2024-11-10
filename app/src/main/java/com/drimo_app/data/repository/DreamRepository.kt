package com.drimo_app.data.repository

import com.drimo_app.data.api.DreamApi
import com.drimo_app.util.Constants.Companion.apiKey
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DreamRepository @Inject constructor(private val dreamApi: DreamApi) {

    suspend fun addDream(title: String, description: String, tags: List<String>, sleepFactors: List<String>): Response<Void> {
        // Formatear la fecha a un formato estándar
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        val formattedDate = dateFormat.format(Date())

        val registerData = mapOf(
            "title" to title,
            "description" to description,
            "tags" to tags,
            "sleepFactors" to sleepFactors,
            "dateDream" to formattedDate,
            "user_id" to 1
        )

        return dreamApi.addDream(
            apikey = apiKey,
            authorization = "Bearer $apiKey",
            addDreamRequest = registerData
        )
    }
}