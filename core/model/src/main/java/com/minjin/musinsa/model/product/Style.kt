package com.minjin.musinsa.model.product

import androidx.compose.runtime.Stable
import com.minjin.musinsa.domain.entity.product.StyleEntity

@Stable
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