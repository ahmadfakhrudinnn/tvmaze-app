package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.TvShow
import com.example.myapplication.data.repository.TvShowRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class TvShowUiState {
    object Loading : TvShowUiState()
    data class Success(val shows: List<TvShow>) : TvShowUiState()
    data class Error(val message: String) : TvShowUiState()
}

class TvShowViewModel(
    private val repository: TvShowRepository = TvShowRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<TvShowUiState>(
        TvShowUiState.Loading
    )

    val uiState: StateFlow<TvShowUiState> = _uiState

    init {
        getShows()
    }

    fun getShows() {
        viewModelScope.launch {
            _uiState.value = TvShowUiState.Loading

            try {
                val shows = repository.getShows()
                _uiState.value = TvShowUiState.Success(shows)
            } catch (e: Exception) {
                _uiState.value = TvShowUiState.Error(
                    e.message ?: "Terjadi kesalahan"
                )
            }
        }
    }
}