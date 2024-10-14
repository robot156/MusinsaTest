package com.minjin.musinsa.model.component.header

import com.minjin.musinsa.domain.entity.component.header.HeaderEntity
import com.minjin.musinsa.model.component.UiType

data class UiHeader(
    val title: String,
    val iconUrl: String? = null,
    val linkUrl: String? = null
) : UiType

fun HeaderEntity.asItem(): UiHeader {
    return UiHeader(
        title,
        iconUrl,
        linkUrl
    )
}