package com.minjin.musinsa.designsystem.component.consume.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.designsystem.image.NetworkImage
import com.minjin.musinsa.designsystem.modifier.onClick
import com.minjin.musinsa.model.component.content.UiContent
import com.minjin.musinsa.model.product.Style
import com.minjin.musinsa.model.state.ContentUiAction
import com.minjin.musinsa.model.state.UiAction

@Composable
fun ConsumeContentStyleUi(
    modifier: Modifier = Modifier,
    uiContent: UiContent.UiStyle,
    onUiAction: (UiAction) -> Unit = {},
) {
    val items = uiContent.styles.chunked(3)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items.forEachIndexed { listIndex, styles ->
            if (listIndex == 0 && styles.size == 3) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    StyleImage(
                        modifier = Modifier.weight(2f),
                        style = styles[0],
                        onUiAction = onUiAction
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        StyleImage(
                            style = styles[1],
                            onUiAction = onUiAction
                        )

                        StyleImage(
                            style = styles[2],
                            onUiAction = onUiAction
                        )
                    }
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    (0..2).forEach { index ->
                        val item = styles.getOrNull(index)

                        if (item != null) {
                            StyleImage(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(250.dp),
                                style = item,
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
}

@Composable
private fun StyleImage(
    modifier: Modifier = Modifier,
    style: Style,
    onUiAction: (UiAction) -> Unit = {},
) {
    NetworkImage(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .onClick { onUiAction(ContentUiAction.OnClickUrl(style.linkUrl)) },
        imageUrl = style.thumbnailUrl
    )
}