package com.pdmcourse2026.basictemplate.screens.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmcourse2026.basictemplate.model.options
import kotlin.collections.component1
import kotlin.collections.component2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  onOptionClick: (String) -> Unit,
  viewModel: HomeViewModel = viewModel()
) {
  val options by viewModel.uiState.collectAsState()
  val loading by viewModel.loading.collectAsState()
  val error by viewModel.error.collectAsState()

  Scaffold(
    topBar = {
      TopAppBar(
        colors = topAppBarColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
          titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text("RankeUca - Vota") },
      )
    }
  ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
      if (loading) {
        Text("Cargando...")
      } else if (error != null) {
        Text("Error: $error")
      } else {
        Column(modifier = Modifier.padding(innerPadding)) {
          Row(modifier = Modifier.padding(8.dp)) {
            Button(onClick = { onOptionClick("option") }) {
              Text(text = "Votaciones")
            }
          }
        }
      }
    }
  }
}