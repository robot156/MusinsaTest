package com.minjin.musinsa.data.datasource.remote

import com.minjin.musinsa.domain.entity.component.UiContainerEntity

internal interface ListRemoteDatasource {

    suspend fun getInterviewList() : UiContainerEntity
}