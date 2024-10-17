package com.minjin.musinsa.designsystem.component.consume.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.model.component.content.UiContent
import com.minjin.musinsa.model.state.UiAction


@Composable
fun ConsumeContentScrollUi(
    modifier: Modifier = Modifier,
    uiContent: UiContent.UiScroll,
    onUiAction: (UiAction) -> Unit = {},
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(uiContent.scroll) {
        lazyListState.scrollToItem(0)
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        state = lazyListState,
    ) {
        items(
            items = uiContent.scroll,
            key = { it.linkUrl }
        ) {
            ContentGoodsItem(
                modifier = Modifier.width(120.dp),
                goods = it,
                onUiAction = onUiAction
            )
        }
    }
}