package com.pdmcourse2026.basictemplate.screens.questions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(
    onQuestionClick: (Int) -> Unit,
    viewModel: QuestionsViewModel = viewModel(factory = QuestionsViewModel.Factory)
) {
    val questions by viewModel.questions.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }
    var newQuestionTitle by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RankeUCA - Preguntas") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir pregunta")
            }
        }
    ) { innerPadding ->
        if (questions.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay preguntas. Añade una para empezar.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(questions) { question ->
                    ListItem(
                        headlineContent = { Text(question.title) },
                        supportingContent = { Text("${question.optionCount} opciones") },
                        trailingContent = {
                            IconButton(onClick = { viewModel.deleteQuestion(question) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Borrar", tint = MaterialTheme.colorScheme.error)
                            }
                        },
                        modifier = Modifier.clickable { onQuestionClick(question.id) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Nueva Pregunta") },
            text = {
                TextField(
                    value = newQuestionTitle,
                    onValueChange = { newQuestionTitle = it },
                    label = { Text("Título de la pregunta") }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (newQuestionTitle.isNotBlank()) {
                        viewModel.addQuestion(newQuestionTitle)
                        newQuestionTitle = ""
                        showDialog = false
                    }
                }) {
                    Text("Añadir")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
