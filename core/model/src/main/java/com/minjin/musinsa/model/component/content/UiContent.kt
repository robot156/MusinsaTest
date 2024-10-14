package com.minjin.musinsa.model.component.content

import com.minjin.musinsa.domain.entity.component.content.ContentEntity
import com.minjin.musinsa.domain.entity.product.BannerEntity
import com.minjin.musinsa.domain.entity.product.GoodsEntity
import com.minjin.musinsa.domain.entity.product.StyleEntity
import com.minjin.musinsa.model.component.UiType
import com.minjin.musinsa.model.product.Banner
import com.minjin.musinsa.model.product.Goods
import com.minjin.musinsa.model.product.Style
import com.minjin.musinsa.model.product.asItem

sealed interface UiContent : UiType {
    data class UiBanner(val banners: List<Banner>) : UiContent
    data class UiGrid(val grid: List<Goods>) : UiContent
    data class UiScroll(val scroll: List<Goods>) : UiContent
    data class UiStyle(val styles: List<Style>) : UiContent
    data object UnknownContent : UiContent
}

fun ContentEntity.asItem(): UiContent {
    return when (this) {
        is ContentEntity.BannerTypeContentEntity -> UiContent.UiBanner(banners.map(BannerEntity::asItem))
        is ContentEntity.GridTypeContent -> UiContent.UiGrid(goods.map(GoodsEntity::asItem))
        is ContentEntity.ScrollTypeContent -> UiContent.UiScroll(goods.map(GoodsEntity::asItem))
        is ContentEntity.StyleTypeContent -> UiContent.UiStyle(styles.map(StyleEntity::asItem))
        else -> UiContent.UnknownContent
    }
}