package com.minjin.musinsa.model.state

interface UiState

inline fun <reified S : UiState> UiState.checkState(action: S.() -> Unit) {
    (this as? S)?.let(action)
}