package com.minjin.musinsa.designsystem.component.consume

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.designsystem.R
import com.minjin.musinsa.model.state.UiAction
import com.minjin.musinsa.model.state.UnknownUiAction

@Composable
fun ConsumeUnknownUi(
    modifier: Modifier = Modifier,
    onUiAction: (UiAction) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.surface,
        ),
        onClick = { onUiAction(UnknownUiAction.OnClickPlayStore) }
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = stringResource(R.string.common_unknown_ui),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun ConsumeUnknownUiPreview() {
    MaterialTheme {
        ConsumeUnknownUi()
    }
}