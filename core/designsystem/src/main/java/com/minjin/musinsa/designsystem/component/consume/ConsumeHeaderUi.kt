package com.minjin.musinsa.designsystem.component.consume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.designsystem.R
import com.minjin.musinsa.designsystem.image.NetworkImage
import com.minjin.musinsa.model.component.header.UiHeader
import com.minjin.musinsa.model.state.HeaderUiAction
import com.minjin.musinsa.model.state.UiAction

@Composable
fun ConsumeHeaderUi(
    modifier: Modifier = Modifier,
    uiHeader: UiHeader,
    onUiAction: (UiAction) -> Unit = {},
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = uiHeader.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                )

                if (uiHeader.iconUrl != null) {
                    Spacer(Modifier.width(8.dp))
                    NetworkImage(imageUrl = uiHeader.iconUrl)
                }
            }
        },
        actions = {
            if (uiHeader.linkUrl != null) {
                TextButton(
                    colors = ButtonDefaults
                        .textButtonColors()
                        .copy(contentColor = MaterialTheme.colorScheme.onSurface),
                    onClick = { onUiAction(HeaderUiAction.OnClickUrl(uiHeader.linkUrl!!)) }
                ) {
                    Text(text = stringResource(R.string.common_all))
                }
            }
        }
    )
}

@Preview
@Composable
private fun ConsumeHeaderUiPreview() {
    MaterialTheme {
        Column {
            ConsumeHeaderUi(
                uiHeader = UiHeader(
                    title = "클리어런스",
                    iconUrl = "https://image.msscdn.net/icons/mobile/clock.png",
                    linkUrl = "aaa"
                )
            )
        }
    }
}