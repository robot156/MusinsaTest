package com.minjin.musinsa.data.model.component.content

import com.minjin.musinsa.data.model.component.UiType
import com.minjin.musinsa.data.model.product.Banner
import com.minjin.musinsa.data.model.product.Goods
import com.minjin.musinsa.data.model.product.Style
import com.minjin.musinsa.data.model.product.asEntity
import com.minjin.musinsa.domain.entity.component.content.ContentEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed class ContentType : UiType() {

    @Serializable
    @SerialName("BANNER")
    internal data class BannerTypeContent(
        override val type: String = "BANNER",
        @SerialName("banners")
        val banners: List<Banner>
    ) : ContentType()

    @Serializable
    @SerialName("GRID")
    internal data class GridTypeContent(
        override val type: String = "GRID",
        @SerialName("goods")
        val goods: List<Goods>
    ) : ContentType()

    @Serializable
    @SerialName("SCROLL")
    internal data class ScrollTypeContent(
        override val type: String = "SCROLL",
        @SerialName("goods")
        val goods: List<Goods>
    ) : ContentType()

    @Serializable
    @SerialName("STYLE")
    internal data class StyleTypeContent(
        override val type: String = "STYLE",
        @SerialName("styles")
        val styles: List<Style>
    ) : ContentType()
}


internal fun ContentType.asEntity(): ContentEntity {
    return when (this) {
        is ContentType.BannerTypeContent -> ContentEntity.BannerTypeContentEntity(banners.map(Banner::asEntity))
        is ContentType.GridTypeContent -> ContentEntity.GridTypeContent(goods.map(Goods::asEntity))
        is ContentType.ScrollTypeContent -> ContentEntity.ScrollTypeContent(goods.map(Goods::asEntity))
        is ContentType.StyleTypeContent -> ContentEntity.StyleTypeContent(styles.map(Style::asEntity))
    }
}