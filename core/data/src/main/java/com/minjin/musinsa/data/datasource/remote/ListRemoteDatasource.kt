package com.minjin.musinsa.data.datasource.remote

internal interface ListRemoteDatasource {

    suspend fun getInterviewList()
}