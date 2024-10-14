package com.minjin.musinsa.model.product

import com.minjin.musinsa.domain.entity.product.BannerEntity

data class Banner(
    val linkUrl: String,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val keyword: String
)

fun BannerEntity.asItem(): Banner {
    return Banner(
        linkUrl = linkUrl,
        thumbnailUrl = thumbnailUrl,
        title = title,
        description = description,
        keyword = keyword
    )
}