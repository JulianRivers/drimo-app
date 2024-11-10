package com.drimo_app.data.api

import com.drimo_app.util.Constants.Companion.BASE_URL
import com.drimo_app.model.start.UserModel;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header
import retrofit2.http.Query

interface AuthApi {

    @GET("users")
    suspend fun login(
        @Header("apikey") apikey: String,
        @Header("Authorization") authorization: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("select") select: String = "*",
    ): Response<List<UserModel>>
}
