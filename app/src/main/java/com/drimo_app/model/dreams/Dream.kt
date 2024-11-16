package com.drimo_app.model.dreams

data class Dream(
    val id: Int,
    val title: String,
    val description: String,
    val dateDream: String,
    val tags: List<String>,
    val sleepFactors: List<String>,
    val user_id: Int
)