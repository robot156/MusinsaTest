package com.minjin.musinsa.data.model.product

import com.minjin.musinsa.domain.entity.product.StyleEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Style(
    @SerialName("linkURL")
    val linkUrl: String,
    @SerialName("thumbnailURL")
    val thumbnailUrl: String
)

internal fun Style.asEntity(): StyleEntity {
    return StyleEntity(
        linkUrl = linkUrl,
        thumbnailUrl = thumbnailUrl
    )
}