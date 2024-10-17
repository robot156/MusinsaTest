package com.minjin.musinsa.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minjin.musinsa.domain.usecase.list.GetInterviewListUseCase
import com.minjin.musinsa.domain.util.Result
import com.minjin.musinsa.domain.util.asResult
import com.minjin.musinsa.model.component.UiContainer
import com.minjin.musinsa.model.component.asItem
import com.minjin.musinsa.model.component.content.UiContent
import com.minjin.musinsa.model.state.ContentUiAction
import com.minjin.musinsa.model.state.ErrorUiAction
import com.minjin.musinsa.model.state.FooterUiAction
import com.minjin.musinsa.model.state.HeaderUiAction
import com.minjin.musinsa.model.state.UiAction
import com.minjin.musinsa.model.state.UiEvent
import com.minjin.musinsa.model.state.UiState
import com.minjin.musinsa.model.state.UnknownUiAction
import com.minjin.musinsa.model.state.checkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _listUiEvent = MutableSharedFlow<ListUiEvent>()
    val listUiEvent: SharedFlow<ListUiEvent> = _listUiEvent

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
            is HeaderUiAction.OnClickUrl -> setListUiEvent(ListUiEvent.NavigateToWebSite(uiAction.url))
            is ContentUiAction.OnClickUrl -> setListUiEvent(ListUiEvent.NavigateToWebSite(uiAction.url))
            is UnknownUiAction.OnClickPlayStore -> setListUiEvent(ListUiEvent.NavigateToWebSite(uiAction.url))
            is FooterUiAction.OnClickRefresh -> setContentRefresh(uiAction.uuid)
            is FooterUiAction.OnClickMore -> setContentMore(uiAction.uuid)
            is ErrorUiAction.OnClickRefresh -> onRefresh()
        }
    }

    private fun setContentRefresh(uuid: String) {
        listUiState.value.checkState<ListUiState.Success> {
            val index = uiContainer.components.indexOfFirst { it.uuid == uuid }

            if (index != -1) {
                uiContainer.components.toMutableList().apply {
                    val component = this[index].uiContent.let { content ->
                        when (content) {
                            is UiContent.UiBanner -> content.copy(banners = content.banners.immutableShuffle())
                            is UiContent.UiGrid -> content.copy(grid = content.grid.immutableShuffle())
                            is UiContent.UiScroll -> content.copy(scroll = content.scroll.immutableShuffle())
                            is UiContent.UiStyle -> content.copy(styles = content.styles.immutableShuffle())
                            else -> return
                        }
                    }

                    set(index, this[index].copy(uiContent = component))
                }.let { components ->
                    _listUiState.update {
                        copy(uiContainer = uiContainer.copy(components = components.toPersistentList()))
                    }
                }
            }
        }
    }

    private fun setContentMore(uuid: String) {
        listUiState.value.checkState<ListUiState.Success> {
            val index = uiContainer.components.indexOfFirst { it.uuid == uuid }

            if (index != -1) {
                uiContainer.components.toMutableList().apply {
                    val component = this[index].uiContent.let { content ->
                        when (content) {
                            is UiContent.UiBanner -> content.copy(banners = (content.banners + content.banners).toPersistentList())
                            is UiContent.UiGrid -> content.copy(grid = (content.grid + content.grid).toPersistentList())
                            is UiContent.UiScroll -> content.copy(scroll = (content.scroll + content.scroll).toPersistentList())
                            is UiContent.UiStyle -> content.copy(styles = (content.styles + content.styles).toPersistentList())
                            else -> return
                        }
                    }

                    set(
                        index = index,
                        element = this[index].copy(uiContent = component, uiFooter = null)
                    )
                }.let { components ->
                    _listUiState.update {
                        copy(uiContainer = uiContainer.copy(components = components.toPersistentList()))
                    }
                }
            }
        }
    }

    private fun onRefresh() {
        viewModelScope.launch {
            refreshSignal.emit(Unit)
        }
    }

    private fun setListUiEvent(uiEvent: ListUiEvent) {
        viewModelScope.launch {
            _listUiEvent.emit(uiEvent)
        }
    }

    private fun <T> List<T>.immutableShuffle(): ImmutableList<T> {
        return this.shuffled().toPersistentList()
    }
}

sealed interface ListUiState : UiState {
    data object Loading : ListUiState
    data class Success(val uiContainer: UiContainer) : ListUiState
    data object Error : ListUiState
}

sealed interface ListUiEvent : UiEvent {
    data class NavigateToWebSite(val linkUrl: String) : ListUiEvent
}