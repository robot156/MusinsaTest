package com.minjin.musinsa.designsystem.component.consume

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.designsystem.component.consume.content.ConsumeContentBannerUi
import com.minjin.musinsa.designsystem.component.consume.content.ConsumeContentGridUi
import com.minjin.musinsa.designsystem.component.consume.content.ConsumeContentScrollUi
import com.minjin.musinsa.designsystem.component.consume.content.ConsumeContentStyleUi
import com.minjin.musinsa.model.component.content.UiContent
import com.minjin.musinsa.model.state.UiAction

@Composable
fun ConsumeContentUi(
    modifier: Modifier = Modifier,
    uiContent: UiContent,
    onUiAction: (UiAction) -> Unit = {},
) {
    when (uiContent) {
        is UiContent.UiBanner -> ConsumeContentBannerUi(
            modifier = modifier,
            uiContent = uiContent,
            onUiAction = onUiAction
        )

        is UiContent.UiGrid -> ConsumeContentGridUi(
            modifier = modifier.padding(horizontal = 12.dp),
            uiContent = uiContent,
            onUiAction = onUiAction
        )

        is UiContent.UiScroll -> ConsumeContentScrollUi(
            modifier = modifier,
            uiContent = uiContent,
            onUiAction = onUiAction
        )

        is UiContent.UiStyle -> ConsumeContentStyleUi(
            modifier = modifier.padding(horizontal = 12.dp),
            uiContent = uiContent,
            onUiAction = onUiAction
        )

        is UiContent.UnknownContent -> ConsumeUnknownUi(
            modifier = modifier.padding(horizontal = 12.dp),
            onUiAction = onUiAction
        )
    }
}