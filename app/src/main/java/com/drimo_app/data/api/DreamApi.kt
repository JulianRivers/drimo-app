package com.drimo_app.data.api

import com.drimo_app.model.dreams.AddDreamRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface DreamApi {

    @POST("dreams")
    @Headers("Content-Type: application/json", "Prefer: return=minimal")
    suspend fun addDream(
        @Header("apikey") apikey: String,
        @Header("Authorization") authorization: String,
        @Body addDreamRequest: AddDreamRequest
    ): Response<Void>
}