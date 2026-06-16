package com.pdmcourse2026.basictemplate.model

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Int = 0,
    val title: String,
    val optionCount: Int = 0
)
