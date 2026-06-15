package com.pdmcourse2026.basictemplate.screens.results

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaApi
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaApiImpl
import com.pdmcourse2026.basictemplate.model.options
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ResultsViewModel : ViewModel() {
    private val repository: RankeUcaApi = RankeUcaApiImpl()

    private val _uiState = MutableStateFlow<List<options>>(emptyList())
    val uiState: StateFlow<List<options>> = _uiState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadOptions() {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            repository.getOptions()
                .onSuccess { optionsList ->
                    _uiState.value = optionsList.sortedByDescending { it.votes }
                }
                .onFailure { e ->
                    _error.value = "Error al cargar el menú: ${e.message}"
                }
            _loading.value = false
        }
    }

}

