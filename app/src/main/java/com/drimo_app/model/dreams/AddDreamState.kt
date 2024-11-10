package com.drimo_app.model.dreams

data class AddDreamState (
    val title : String = "",
    val description : String = "",
    val tags: List<String> = emptyList(),
    val sleepFactors: List<String> = emptyList()
)

data class AddDreamRequest(
    val title: String,
    val description: String,
    val tags: List<String>,
    val sleepFactors: List<String>,
    val dateDream: String,
    val user_id: Int
)