package com.minjin.musinsa.domain.entity.component.footer

import com.minjin.musinsa.domain.entity.Entity

sealed interface FooterEntity : Entity {

    data class RefreshTypeFooterEntity(
        val title: String,
        val iconUrl: String
    ) : FooterEntity

    data class MoreTypeFooterEntity(
        val title: String,
    ) : FooterEntity

    data object UnknownFooterEntity : FooterEntity
}
