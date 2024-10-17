package com.minjin.musinsa.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minjin.musinsa.domain.usecase.list.GetInterviewListUseCase
import com.minjin.musinsa.domain.util.Result
import com.minjin.musinsa.domain.util.asResult
import com.minjin.musinsa.model.component.UiContainer
import com.minjin.musinsa.model.component.asItem
import com.minjin.musinsa.model.state.ContentUiAction
import com.minjin.musinsa.model.state.ErrorUiAction
import com.minjin.musinsa.model.state.FooterUiAction
import com.minjin.musinsa.model.state.HeaderUiAction
import com.minjin.musinsa.model.state.UiAction
import com.minjin.musinsa.model.state.UiState
import com.minjin.musinsa.model.state.UnknownUiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getInterviewListUseCase: GetInterviewListUseCase
) : ViewModel() {

    private val refreshSignal = MutableSharedFlow<Unit>()

    private val loadDataSignal: Flow<Unit> = flow {
        emit(Unit)
        emitAll(refreshSignal)
    }

    private val interviewListResult = loadDataSignal
        .flatMapLatest { getInterviewListUseCase(Unit).asResult() }
        .stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)

    private val _listUiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val listUiState: StateFlow<ListUiState> = _listUiState

    init {
        viewModelScope.launch {
            interviewListResult.collect { result ->
                _listUiState.update {
                    when (result) {
                        is Result.Loading -> ListUiState.Loading
                        is Result.Success -> ListUiState.Success(uiContainer = result.data.asItem())
                        is Result.Error -> ListUiState.Error
                    }
                }
            }
        }
    }

    fun onUiAction(uiAction: UiAction) {
        when (uiAction) {
            is HeaderUiAction.OnClickUrl -> {}
            is ContentUiAction.OnClickUrl -> {}
            is FooterUiAction.OnClickRefresh -> {}
            is FooterUiAction.OnClickMore -> {}
            is UnknownUiAction.OnClickPlayStore -> {}
            is ErrorUiAction.OnClickRefresh -> onRefresh()
        }
    }

    private fun onRefresh() {
        viewModelScope.launch {
            refreshSignal.emit(Unit)
        }
    }
}

sealed interface ListUiState : UiState {
    data object Loading : ListUiState
    data class Success(val uiContainer: UiContainer) : ListUiState
    data object Error : ListUiState
}