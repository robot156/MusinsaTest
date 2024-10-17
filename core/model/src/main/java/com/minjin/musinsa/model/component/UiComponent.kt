package com.minjin.musinsa.model.component

import androidx.compose.runtime.Stable
import com.minjin.musinsa.domain.entity.component.UiComponentEntity
import com.minjin.musinsa.domain.entity.component.UiContainerEntity
import com.minjin.musinsa.model.component.content.UiContent
import com.minjin.musinsa.model.component.content.asItem
import com.minjin.musinsa.model.component.footer.UiFooter
import com.minjin.musinsa.model.component.footer.asItem
import com.minjin.musinsa.model.component.header.UiHeader
import com.minjin.musinsa.model.component.header.asItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import java.util.UUID

@Stable
data class UiContainer(
    val components: ImmutableList<UiComponent>
)

@Stable
data class UiComponent(
    val uuid: String = UUID.randomUUID().toString(),
    val uiHeader: UiHeader?, // header
    val uiContent: UiContent?, // banner, grid, scroll, style
    val uiFooter: UiFooter? // Refresh, More
)

fun UiContainerEntity.asItem(): UiContainer {
    return UiContainer(
        components = components.map(UiComponentEntity::asItem).toPersistentList()
    )
}

fun UiComponentEntity.asItem(): UiComponent {
    return UiComponent(
        uiHeader = header?.asItem(),
        uiContent = content?.asItem(),
        uiFooter = footer?.asItem()
    )
}