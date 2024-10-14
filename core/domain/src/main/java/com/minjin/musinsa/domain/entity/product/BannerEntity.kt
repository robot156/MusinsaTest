package com.minjin.musinsa.domain.entity.product

import com.minjin.musinsa.domain.entity.Entity

data class BannerEntity(
    val linkUrl: String,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val keyword: String
) : Entity