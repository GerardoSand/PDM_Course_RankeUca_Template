package com.pdmcourse2026.basictemplate.model

import kotlinx.serialization.Serializable


@Serializable
data class options(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int
)

@Serializable
data class VoteRequest(
    val optionId: Int
)
