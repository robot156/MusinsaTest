package com.minjin.musinsa.data.model.product

import com.minjin.musinsa.domain.entity.product.BannerEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Banner(
    @SerialName("linkURL")
    val linkUrl: String,
    @SerialName("thumbnailURL")
    val thumbnailUrl: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("keyword")
    val keyword: String
)

internal fun Banner.asEntity(): BannerEntity {
    return BannerEntity(
        linkUrl = linkUrl,
        thumbnailUrl = thumbnailUrl,
        title = title,
        description = description,
        keyword = keyword
    )
}