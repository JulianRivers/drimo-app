package com.drimo_app.data.repository

import com.drimo_app.data.api.AuthApi
import com.drimo_app.model.start.UserModel
import retrofit2.Response
import javax.inject.Inject
import com.drimo_app.util.Constants.Companion.apiKey

class AuthRepository @Inject constructor(private val authApi: AuthApi) {

    suspend fun login(email: String, password: String): Response<List<UserModel>> {
        return authApi.login(
            apikey = apiKey,
            authorization = "Bearer $apiKey",
            email = email,
            password = password
        )
    }
}