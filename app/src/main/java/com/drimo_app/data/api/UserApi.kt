package com.drimo_app.data.api

import com.drimo_app.model.start.UserModel;

import retrofit2.Response;
import retrofit2.http.Body
import retrofit2.http.GET;
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun login(
        @Header("apikey") apikey: String,
        @Header("Authorization") authorization: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("select") select: String = "*",
    ): Response<List<UserModel>>

    @POST("users")
    @Headers("Content-Type: application/json", "Prefer: return=minimal")
    suspend fun register(
        @Header("apikey") apikey: String,
        @Header("Authorization") authorization: String,
        @Body registerRequest: Map<String, String>
    ): Response<Void>
}
