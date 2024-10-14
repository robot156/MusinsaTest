package com.minjin.musinsa.data.service

import com.minjin.musinsa.data.model.component.UiContainer
import retrofit2.http.GET

internal interface InterviewApi {

    @GET("interview/list.json")
    suspend fun getInterviewList() : UiContainer
}