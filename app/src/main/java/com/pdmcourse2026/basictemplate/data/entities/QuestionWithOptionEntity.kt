package com.pdmcourse2026.basictemplate.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.pdmcourse2026.basictemplate.model.Question

data class QuestionWithOptions(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumns = ["id"],
        entityColumns = ["questionId"]
    )
    val options: List<OptionEntity>
)

fun QuestionWithOptions.toModel(): Question {
    return Question(
        id = question.id,
        title = question.title,
        optionCount = options.size,
    )
}
