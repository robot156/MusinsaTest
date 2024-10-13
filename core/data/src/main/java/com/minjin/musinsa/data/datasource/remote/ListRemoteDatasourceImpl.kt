package com.minjin.musinsa.data.datasource.remote

import com.minjin.musinsa.data.service.InterviewApi
import javax.inject.Inject

internal class ListRemoteDatasourceImpl @Inject constructor(
    private val interviewApi: InterviewApi
) : ListRemoteDatasource {

    override suspend fun getInterviewList() {
        return interviewApi.getInterviewList()
    }
}