package com.minjin.musinsa.data.datasource.remote

import com.minjin.musinsa.data.model.component.asEntity
import com.minjin.musinsa.data.service.InterviewApi
import com.minjin.musinsa.domain.entity.component.UiContainerEntity
import javax.inject.Inject

internal class ListRemoteDatasourceImpl @Inject constructor(
    private val interviewApi: InterviewApi
) : ListRemoteDatasource {

    override suspend fun getInterviewList(): UiContainerEntity {
        return try {
            interviewApi.getInterviewList().asEntity()
        } catch (error: Exception) {
            throw error
        }
    }
}