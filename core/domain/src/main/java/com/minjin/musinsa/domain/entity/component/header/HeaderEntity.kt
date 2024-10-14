package com.minjin.musinsa.domain.entity.component.header

import com.minjin.musinsa.domain.entity.Entity

data class HeaderEntity(
    val title: String,
    val iconUrl: String? = "",
    val linkUrl: String? = ""
) : Entity