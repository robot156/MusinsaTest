package com.minjin.musinsa.domain.entity.component.content

import com.minjin.musinsa.domain.entity.Entity
import com.minjin.musinsa.domain.entity.product.BannerEntity
import com.minjin.musinsa.domain.entity.product.GoodsEntity
import com.minjin.musinsa.domain.entity.product.StyleEntity

sealed interface ContentEntity : Entity {

    data class BannerTypeContentEntity(
        val banners: List<BannerEntity>
    ) : ContentEntity

    data class GridTypeContent(
        val goods: List<GoodsEntity>
    ) : ContentEntity

    data class ScrollTypeContent(
        val goods: List<GoodsEntity>
    ) : ContentEntity

    data class StyleTypeContent(
        val styles: List<StyleEntity>
    ) : ContentEntity

    data object UnknownContent : ContentEntity
}
