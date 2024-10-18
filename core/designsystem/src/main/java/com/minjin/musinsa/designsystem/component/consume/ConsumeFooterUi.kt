package com.minjin.musinsa.designsystem.component.consume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.designsystem.component.FooterButton
import com.minjin.musinsa.designsystem.image.NetworkImage
import com.minjin.musinsa.model.component.footer.UiFooter
import com.minjin.musinsa.model.state.FooterUiAction
import com.minjin.musinsa.model.state.UiAction

@Composable
fun ConsumeFooterUi(
    modifier: Modifier = Modifier,
    uuid : String = "",
    uiFooter: UiFooter,
    onUiAction: (UiAction) -> Unit = {},
) {
    when (uiFooter) {
        is UiFooter.Refresh -> ConsumeRefreshFooterUi(
            modifier = modifier.padding(horizontal = 4.dp, vertical = 24.dp),
            uuid = uuid,
            uiFooter = uiFooter,
            onUiAction = onUiAction
        )

        is UiFooter.More -> ConsumeMoreFooterUi(
            modifier = modifier.padding(horizontal = 4.dp, vertical = 24.dp),
            uuid = uuid,
            uiFooter = uiFooter,
            onUiAction = onUiAction
        )

        is UiFooter.UnknownFooter -> ConsumeUnknownUi(
            modifier = modifier.padding(horizontal = 12.dp),
            onUiAction = onUiAction
        )
    }
}

@Composable
private fun ConsumeRefreshFooterUi(
    modifier: Modifier = Modifier,
    uuid : String = "",
    uiFooter: UiFooter.Refresh,
    onUiAction: (UiAction) -> Unit = {},
) {
    Row(
        modifier = modifier
    ) {
        FooterButton(
            onClick = { onUiAction(FooterUiAction.OnClickRefresh(uuid)) }
        ) {
            NetworkImage(
                imageUrl = uiFooter.iconUrl
            )

            Spacer(Modifier.width(4.dp))

            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = uiFooter.title,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            )
        }
    }
}

@Composable
private fun ConsumeMoreFooterUi(
    modifier: Modifier = Modifier,
    uuid : String = "",
    uiFooter: UiFooter.More,
    onUiAction: (UiAction) -> Unit = {},
) {
    Row(
        modifier = modifier
    ) {
        FooterButton(
            onClick = { onUiAction(FooterUiAction.OnClickMore(uuid)) }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = uiFooter.title,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Composable
private fun ConsumeFooterUiPreview() {
    MaterialTheme {
        Column {
            ConsumeFooterUi(uiFooter = UiFooter.Refresh(title = "새로운 추천", ""))
            Spacer(Modifier.height(4.dp))
            ConsumeFooterUi(uiFooter = UiFooter.More(title = "더보기"))
        }
    }
}