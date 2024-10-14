package com.minjin.musinsa.data.model.component.header

import com.minjin.musinsa.domain.entity.component.header.HeaderEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class HeaderType(
    @SerialName("title")
    val title: String,
    @SerialName("iconURL")
    val iconUrl: String? = "",
    @SerialName("linkURL")
    val linkUrl: String? = ""
)

internal fun HeaderType.asEntity(): HeaderEntity {
    return HeaderEntity(
        title = title,
        iconUrl = iconUrl,
        linkUrl = linkUrl
    )
}