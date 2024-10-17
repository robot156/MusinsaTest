package com.minjin.musinsa.model.component.footer

import androidx.compose.runtime.Stable
import com.minjin.musinsa.domain.entity.component.footer.FooterEntity
import com.minjin.musinsa.model.component.UiType

@Stable
sealed interface UiFooter : UiType {
    data class Refresh(val title: String, val iconUrl: String) : UiFooter
    data class More(val title: String) : UiFooter
    data object UnknownFooter : UiFooter
}

fun FooterEntity.asItem(): UiFooter {
    return when (this) {
        is FooterEntity.RefreshTypeFooterEntity -> UiFooter.Refresh(title, iconUrl)
        is FooterEntity.MoreTypeFooterEntity -> UiFooter.More(title)
        else -> UiFooter.UnknownFooter
    }
}