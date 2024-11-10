package com.drimo_app.model.patterns

data class SleepPattern(
    val factors: List<Factor>,
    val tags: List<String>,
    val completedCycles: Int,
    val plannedHours: Int
)

data class Factor(
    val name: String,
    val count: Int
)
