package com.minjin.musinsa.designsystem.component.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.designsystem.R
import com.minjin.musinsa.model.state.ErrorUiAction
import com.minjin.musinsa.model.state.UiAction

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onUiAction: (UiAction) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.common_error),
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = stringResource(id = R.string.common_error_description),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = { onUiAction(ErrorUiAction.OnClickRefresh) }
        ) {
            Text(
                text = stringResource(R.string.common_refresh)
            )
        }
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    MaterialTheme {
        ErrorScreen { }
    }
}