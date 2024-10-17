package com.minjin.musinsa.designsystem.component.consume.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.model.component.content.UiContent
import com.minjin.musinsa.model.state.UiAction

@Composable
fun ConsumeContentGridUi(
    modifier: Modifier = Modifier,
    uiContent: UiContent.UiGrid,
    onUiAction: (UiAction) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        uiContent.grid.chunked(3).forEach { list ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                (0..2).forEach {
                    val item = list.getOrNull(it)

                    if (item != null) {
                        ContentGoodsItem(
                            modifier = Modifier.weight(1f),
                            goods = item,
                            onUiAction = onUiAction
                        )
                    } else {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }
}