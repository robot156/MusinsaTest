package com.minjin.musinsa.model.state

interface UiAction

sealed interface HeaderUiAction : UiAction {
    data class OnClickUrl(val url: String) : HeaderUiAction
}

sealed interface ContentUiAction : UiAction {
    data class OnClickUrl(val url: String) : ContentUiAction
}

sealed interface FooterUiAction : UiAction {
    data object OnClickRefresh : FooterUiAction
    data object OnClickMore : FooterUiAction
}

sealed interface UnknownUiAction : UiAction {
    data object OnClickPlayStore : UnknownUiAction
}

sealed interface ErrorUiAction : UiAction {
    data object OnClickRefresh : ErrorUiAction
}