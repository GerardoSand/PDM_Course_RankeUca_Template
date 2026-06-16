package com.pdmcourse2026.basictemplate

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.screens.home.HomeScreen
import com.pdmcourse2026.basictemplate.screens.questions.QuestionsScreen
import com.pdmcourse2026.basictemplate.screens.results.ResultScreen

@Composable
fun RankeUCA_App() {
    val backStack = rememberNavBackStack(Routes.Questions)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Questions> {
                QuestionsScreen(
                    onQuestionClick = { questionId ->
                        backStack.add(Routes.options(questionId))
                    }
                )
            }
            entry<Routes.options> { route ->
                HomeScreen(
                    questionId = route.questionId,
                    onOptionClick = { optionId ->
                        backStack.add(Routes.Results(route.questionId, optionId))
                    }
                )
            }
            entry<Routes.Results> { route ->
                ResultScreen(
                    questionId = route.questionId,
                    selectedOptionId = route.selectedOptionId,
                    onBackClick = {
                        backStack.removeLastOrNull() // Back to Home
                    }
                )
            }
        },
    )
}
