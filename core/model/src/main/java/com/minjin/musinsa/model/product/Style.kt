package com.minjin.musinsa.model.product

import com.minjin.musinsa.domain.entity.product.StyleEntity

data class Style(
    val linkUrl: String,
    val thumbnailUrl: String
)

fun StyleEntity.asItem(): Style {
    return Style(
        linkUrl = linkUrl,
        thumbnailUrl = thumbnailUrl
    )
}