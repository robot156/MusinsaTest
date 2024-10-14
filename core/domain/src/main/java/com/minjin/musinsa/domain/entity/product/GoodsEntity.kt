package com.minjin.musinsa.domain.entity.product

import com.minjin.musinsa.domain.entity.Entity

data class GoodsEntity(
    val linkUrl: String,
    val thumbnailUrl: String,
    val brandName: String,
    val price: Int,
    val saleRate: Int,
    val hasCoupon: Boolean
) : Entity