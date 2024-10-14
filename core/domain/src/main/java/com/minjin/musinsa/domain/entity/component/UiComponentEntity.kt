package com.minjin.musinsa.domain.entity.component

import com.minjin.musinsa.domain.entity.Entity
import com.minjin.musinsa.domain.entity.component.content.ContentEntity
import com.minjin.musinsa.domain.entity.component.footer.FooterEntity
import com.minjin.musinsa.domain.entity.component.header.HeaderEntity

data class UiContainerEntity(
    val components: List<UiComponentEntity>
) : Entity

data class UiComponentEntity(
    val header: HeaderEntity?,
    val content: ContentEntity?,
    val footer: FooterEntity?
) : Entity