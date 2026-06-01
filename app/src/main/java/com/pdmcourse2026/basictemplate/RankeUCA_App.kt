package com.pdmcourse2026.basictemplate

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.screens.home.HomeScreen
import com.pdmcourse2026.basictemplate.screens.results.ResultScreen

@Composable
fun RankeUCA_App() {

  val backStack = rememberNavBackStack(Routes.options)
  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.options> {
        HomeScreen(
          onOptionClick = { option ->
            backStack.add(Routes.Results)
          }
        )
      }
      entry<Routes.Results> {
      ResultScreen(
        onOptionClick = { option ->
          backStack.add(Routes.options)
        }
      )
      }
    },
  )
}