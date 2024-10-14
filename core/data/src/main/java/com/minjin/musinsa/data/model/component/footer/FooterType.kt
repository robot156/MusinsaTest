package com.minjin.musinsa.data.model.component.footer

import com.minjin.musinsa.data.model.component.UiType
import com.minjin.musinsa.domain.entity.component.footer.FooterEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed class FooterType : UiType() {

    @Serializable
    @SerialName("REFRESH")
    internal data class RefreshTypeFooter(
        override val type: String = "REFRESH",
        @SerialName("title")
        val title: String,
        @SerialName("iconURL")
        val iconUrl: String
    ) : FooterType()

    @Serializable
    @SerialName("MORE")
    internal data class MoreTypeFooter(
        override val type: String = "MORE",
        @SerialName("title")
        val title: String,
    ) : FooterType()
}

internal fun FooterType?.asEntity(): FooterEntity {
    return when (this) {
        is FooterType.RefreshTypeFooter -> FooterEntity.RefreshTypeFooterEntity(title = title, iconUrl = iconUrl)
        is FooterType.MoreTypeFooter -> FooterEntity.MoreTypeFooterEntity(title = title)
        else -> FooterEntity.UnknownFooterEntity
    }
}