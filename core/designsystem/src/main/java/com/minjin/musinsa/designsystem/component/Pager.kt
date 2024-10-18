package com.minjin.musinsa.designsystem.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun AutoScrollablePager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    itemCount: Int,
    scrollTime: Long = PAGER_AUTO_SCROLL_TIME,
    items: @Composable (page: Int) -> Unit,
) {
    LaunchedEffect(pagerState.settledPage) {
        delay(scrollTime)
        pagerState.animateScrollToPage(
            page = pagerState.currentPage + 1,
            animationSpec = tween(1500)
        )
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) {
        val page = getCurrentPage(itemCount, it)
        items(page)
    }
}

private fun getCurrentPage(itemCount: Int, currentPage: Int): Int {
    return when {
        (itemCount + 1 == currentPage) -> 0
        currentPage == 0 -> itemCount - 1
        else -> currentPage - 1
    }
}

private const val PAGER_AUTO_SCROLL_TIME = 3000L