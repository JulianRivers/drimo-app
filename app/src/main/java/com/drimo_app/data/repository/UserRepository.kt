package com.drimo_app.data.repository

import com.drimo_app.data.api.UserApi
import com.drimo_app.model.start.UserModel
import retrofit2.Response
import javax.inject.Inject
import com.drimo_app.util.Constants.Companion.apiKey

class UserRepository @Inject constructor(private val userApi: UserApi) {

    suspend fun login(email: String, password: String): Response<List<UserModel>> {
        return userApi.login(
            apikey = apiKey,
            authorization = "Bearer $apiKey",
            email = email,
            password = password
        )
    }

    suspend fun register(email: String, password: String): Response<Void> {
        val registerData = mapOf(
            "email" to email,
            "password" to password
        )

        return userApi.register(
            apikey = apiKey,
            authorization = "Bearer $apiKey",
            registerRequest = registerData
        )
    }
}