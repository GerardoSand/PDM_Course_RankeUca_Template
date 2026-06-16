package com.pdmcourse2026.basictemplate

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
  @Serializable
  data object Questions : Routes()

  @Serializable
  data class options(val questionId: Int) : Routes()

  @Serializable
  data class Results(val questionId: Int, val selectedOptionId: Int = -1) : Routes()
}
