package com.minjin.musinsa.data.model.component

import com.minjin.musinsa.data.model.component.content.ContentType
import com.minjin.musinsa.data.model.component.content.asEntity
import com.minjin.musinsa.data.model.component.footer.FooterType
import com.minjin.musinsa.data.model.component.footer.asEntity
import com.minjin.musinsa.data.model.component.header.HeaderType
import com.minjin.musinsa.data.model.component.header.asEntity
import com.minjin.musinsa.domain.entity.component.UiComponentEntity
import com.minjin.musinsa.domain.entity.component.UiContainerEntity
import com.minjin.musinsa.domain.entity.component.content.ContentEntity
import com.minjin.musinsa.domain.entity.component.footer.FooterEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UiContainer(
    @SerialName("data")
    val uiComponents: List<UiComponents>
)

@Serializable
internal data class UiComponents(
    @SerialName("header")
    val headerType: HeaderType? = null,
    @SerialName("contents")
    val contentType: UiType? = null,
    @SerialName("footer")
    val footerType: UiType? = null,
)

internal fun UiContainer.asEntity(): UiContainerEntity {
    return UiContainerEntity(components = uiComponents.map(UiComponents::asEntity))
}

internal fun UiComponents.asEntity(): UiComponentEntity {
    return UiComponentEntity(
        header = headerType?.asEntity(),
        content = if (contentType is UnknownTypeContent) ContentEntity.UnknownContent else (contentType as? ContentType)?.asEntity(),
        footer = if (footerType is UnknownTypeContent) FooterEntity.UnknownFooterEntity else (footerType as? FooterType)?.asEntity()
    )
}