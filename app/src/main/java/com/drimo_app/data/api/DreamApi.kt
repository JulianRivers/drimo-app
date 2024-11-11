package com.drimo_app.data.api

import com.drimo_app.model.dreams.AddDreamRequest
import com.drimo_app.model.dreams.Dream
import com.drimo_app.model.start.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface DreamApi {

    @POST("dreams")
    @Headers("Content-Type: application/json", "Prefer: return=minimal")
    suspend fun addDream(
        @Header("apikey") apikey: String,
        @Header("Authorization") authorization: String,
        @Body addDreamRequest: AddDreamRequest
    ): Response<Void>

    @GET("dreams")
    @Headers("Content-Type: application/json")
    suspend fun getDreams(
        @Header("apikey") apikey: String,
        @Header("Authorization") authorization: String,
        @Query("user_id") user_id: String,
        @Query("select") select: String = "*",
    ): Response<List<Dream>>
}