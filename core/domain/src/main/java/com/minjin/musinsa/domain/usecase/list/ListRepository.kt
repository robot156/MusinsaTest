package com.minjin.musinsa.domain.usecase.list

import com.minjin.musinsa.domain.entity.component.UiContainerEntity

interface ListRepository {

    suspend fun getInterviewList() : UiContainerEntity
}