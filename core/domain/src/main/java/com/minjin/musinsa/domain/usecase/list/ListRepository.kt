package com.minjin.musinsa.domain.usecase.list

interface ListRepository {

    suspend fun getInterviewList()
}