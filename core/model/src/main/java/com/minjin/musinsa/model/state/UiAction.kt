package com.minjin.musinsa.model.state

interface UiAction

sealed interface HeaderUiAction : UiAction {
    data class OnClickUrl(val url: String) : HeaderUiAction
}

sealed interface ContentUiAction : UiAction {
    data class OnClickUrl(val url: String) : ContentUiAction
}

sealed interface FooterUiAction : UiAction {
    data class OnClickRefresh(val uuid: String) : FooterUiAction
    data class OnClickMore(val uuid: String) : FooterUiAction
}

sealed interface UnknownUiAction : UiAction {
    data class OnClickPlayStore(val url: String) : UnknownUiAction
}

sealed interface ErrorUiAction : UiAction {
    data object OnClickRefresh : ErrorUiAction
}