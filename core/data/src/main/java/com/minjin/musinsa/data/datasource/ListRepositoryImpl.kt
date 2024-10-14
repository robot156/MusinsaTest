package com.minjin.musinsa.data.datasource

import com.minjin.musinsa.data.datasource.remote.ListRemoteDatasource
import com.minjin.musinsa.domain.entity.component.UiContainerEntity
import com.minjin.musinsa.domain.usecase.list.ListRepository
import javax.inject.Inject

internal class ListRepositoryImpl @Inject constructor(
    private val remoteDatasource: ListRemoteDatasource
) : ListRepository {

    override suspend fun getInterviewList() : UiContainerEntity {
        return remoteDatasource.getInterviewList()
    }
}