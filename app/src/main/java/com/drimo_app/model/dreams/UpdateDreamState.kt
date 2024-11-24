package com.drimo_app.model.dreams

data class UpdateDreamState (
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val tags: List<String> = emptyList(),
    val sleepFactors: List<String> = emptyList(),
)

data class UpdateDreamRequest(
    val title: String,
    val description: String,
    val tags: List<String>,
    val sleepFactors: List<String>,
)