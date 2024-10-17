package com.minjin.musinsa.model.component.content

import androidx.compose.runtime.Stable
import com.minjin.musinsa.domain.entity.component.content.ContentEntity
import com.minjin.musinsa.domain.entity.product.BannerEntity
import com.minjin.musinsa.domain.entity.product.GoodsEntity
import com.minjin.musinsa.domain.entity.product.StyleEntity
import com.minjin.musinsa.model.component.UiType
import com.minjin.musinsa.model.product.Banner
import com.minjin.musinsa.model.product.Goods
import com.minjin.musinsa.model.product.Style
import com.minjin.musinsa.model.product.asItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Stable
sealed interface UiContent : UiType {
    data class UiBanner(val banners: ImmutableList<Banner>) : UiContent
    data class UiGrid(val grid: ImmutableList<Goods>) : UiContent
    data class UiScroll(val scroll: ImmutableList<Goods>) : UiContent
    data class UiStyle(val styles: ImmutableList<Style>) : UiContent
    data object UnknownContent : UiContent
}

fun ContentEntity.asItem(): UiContent {
    return when (this) {
        is ContentEntity.BannerTypeContentEntity -> UiContent.UiBanner(banners.map(BannerEntity::asItem).toPersistentList())
        is ContentEntity.GridTypeContent -> UiContent.UiGrid(goods.map(GoodsEntity::asItem).toPersistentList())
        is ContentEntity.ScrollTypeContent -> UiContent.UiScroll(goods.map(GoodsEntity::asItem).toPersistentList())
        is ContentEntity.StyleTypeContent -> UiContent.UiStyle(styles.map(StyleEntity::asItem).toPersistentList())
        else -> UiContent.UnknownContent
    }
}