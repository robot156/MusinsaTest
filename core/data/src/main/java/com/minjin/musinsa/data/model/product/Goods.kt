package com.minjin.musinsa.data.model.product

import com.minjin.musinsa.domain.entity.product.GoodsEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Goods(
    @SerialName("linkURL")
    val linkUrl: String,
    @SerialName("thumbnailURL")
    val thumbnailUrl: String,
    @SerialName("brandName")
    val brandName: String,
    @SerialName("price")
    val price: Int,
    @SerialName("saleRate")
    val saleRate: Int,
    @SerialName("hasCoupon")
    val hasCoupon: Boolean
)

internal fun Goods.asEntity(): GoodsEntity {
    return GoodsEntity(
        linkUrl = linkUrl,
        thumbnailUrl = thumbnailUrl,
        brandName = brandName,
        price = price,
        saleRate = saleRate,
        hasCoupon = hasCoupon
    )
}