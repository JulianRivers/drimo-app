package com.drimo_app.model.start

data class RegisterState(
    val email : String = "",
    val password : String = "",
    val confirmPassword : String = ""
)

data class RegisterRequest(
    val email: String,
    val password: String,
)