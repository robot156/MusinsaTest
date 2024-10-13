package com.minjin.musinsa.data.service

import retrofit2.http.GET

internal interface InterviewApi {

    @GET("interview/list.json")
    suspend fun getInterviewList()
}