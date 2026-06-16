package com.pdmcourse2026.basictemplate.model

import kotlinx.serialization.Serializable

@Serializable
data class Option(
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val votes: Int = 0,
    val questionId: Int
)
