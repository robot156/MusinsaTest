package com.minjin.musinsa.list

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.minjin.musinsa.designsystem.component.common.ErrorScreen
import com.minjin.musinsa.designsystem.component.common.LoadingScreen
import com.minjin.musinsa.designsystem.component.consume.ConsumeContentUi
import com.minjin.musinsa.designsystem.component.consume.ConsumeFooterUi
import com.minjin.musinsa.designsystem.component.consume.ConsumeHeaderUi
import com.minjin.musinsa.designsystem.util.ObserveAsEvents
import com.minjin.musinsa.model.component.UiContainer
import com.minjin.musinsa.model.state.UiAction

@Composable
internal fun ListScreenRoute(
    viewModel: ListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val listUiState by viewModel.listUiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.listUiEvent) { event ->
        when (event) {
            is ListUiEvent.NavigateToWebSite -> {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.linkUrl))
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when (val uiState = listUiState) {
        is ListUiState.Loading -> LoadingScreen()

        is ListUiState.Success -> ListContainer(
            uiContainer = uiState.uiContainer,
            onUiAction = viewModel::onUiAction
        )

        is ListUiState.Error -> ErrorScreen(onUiAction = viewModel::onUiAction)
    }
}

@Composable
private fun ListContainer(
    modifier: Modifier = Modifier,
    uiContainer: UiContainer,
    onUiAction: (UiAction) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(
            items = uiContainer.components,
            key = { it.uuid }
        ) { uiConsume ->
            uiConsume.uiHeader?.let { uiHeader ->
                ConsumeHeaderUi(
                    modifier = Modifier.animateItem(),
                    uiHeader = uiHeader,
                    onUiAction = onUiAction
                )
            }

            uiConsume.uiContent?.let { uiContent ->
                ConsumeContentUi(
                    modifier = Modifier.animateItem(),
                    uiContent = uiContent,
                    onUiAction = onUiAction
                )
            }

            uiConsume.uiFooter?.let { uiFooter ->
                ConsumeFooterUi(
                    modifier = Modifier.animateItem(),
                    uuid = uiConsume.uuid,
                    uiFooter = uiFooter,
                    onUiAction = onUiAction
                )
            }
        }
    }
}