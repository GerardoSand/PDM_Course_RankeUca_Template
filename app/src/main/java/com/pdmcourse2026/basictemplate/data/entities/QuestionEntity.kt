package com.pdmcourse2026.basictemplate.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdmcourse2026.basictemplate.model.Question

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
)

fun QuestionEntity.toModel(optionCount: Int = 0): Question {
    return Question(
        id = id,
        title = title,
        optionCount = optionCount
    )
}

fun Question.toEntity(): QuestionEntity {
    return QuestionEntity(
        id = id,
        title = title
    )
}
