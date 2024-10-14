package com.minjin.musinsa.model.product

import com.minjin.musinsa.domain.entity.product.GoodsEntity

data class Goods(
    val linkUrl: String,
    val thumbnailUrl: String,
    val brandName: String,
    val price: Int,
    val saleRate: Int,
    val hasCoupon: Boolean
)

fun GoodsEntity.asItem(): Goods {
    return Goods(
        linkUrl = linkUrl,
        thumbnailUrl = thumbnailUrl,
        brandName = brandName,
        price = price,
        saleRate = saleRate,
        hasCoupon = hasCoupon
    )
}