package com.minjin.musinsa.designsystem.component.consume.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.designsystem.component.AutoScrollablePager
import com.minjin.musinsa.designsystem.image.NetworkImage
import com.minjin.musinsa.designsystem.modifier.onClick
import com.minjin.musinsa.model.component.content.UiContent
import com.minjin.musinsa.model.state.ContentUiAction
import com.minjin.musinsa.model.state.UiAction

@Composable
fun ConsumeContentBannerUi(
    modifier: Modifier = Modifier,
    uiContent: UiContent.UiBanner,
    onUiAction: (UiAction) -> Unit = {},
) {
    val pageSize = uiContent.banners.size
    val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })

    Box(
        modifier = modifier.onClick {
            onUiAction(ContentUiAction.OnClickUrl(uiContent.banners[pagerState.currentPage % pageSize].linkUrl))
        }
    ) {
        AutoScrollablePager(
            modifier = Modifier.fillMaxSize(),
            pagerState = pagerState,
            itemCount = pageSize,
        ) { page ->
            val banner = uiContent.banners[page % pageSize]

            Box {
                NetworkImage(
                    modifier = Modifier.fillMaxSize(),
                    imageUrl = banner.thumbnailUrl
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    if (banner.title.isNotBlank()) {
                        Text(
                            modifier = Modifier
                                .background(Color.Black.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp),
                            text = banner.title,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                    }

                    if (banner.description.isNotBlank()) {
                        Text(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .background(Color.Black.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp),
                            text = banner.description,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.5f))
                .align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "${(pagerState.currentPage % pageSize) + 1} / $pageSize",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White
            )
        }
    }
}