package com.pdmcourse2026.basictemplate.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaApi
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaApiImpl
import com.pdmcourse2026.basictemplate.model.options
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DishesViewModel : ViewModel() {
    private val repository: RankeUcaApi = RankeUcaApiImpl()

    private val _uiState = MutableStateFlow<options?>(null)
    val uiState: StateFlow<options?> = _uiState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadRestaurantMenu(id: Int) {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            repository.get
                .onSuccess { restaurant ->
                    _uiState.value = restaurant
                }
                .onFailure { e ->
                    _error.value = "Error al cargar el menú: ${e.message}"
                }
            _loading.value = false
        }
    }
}
