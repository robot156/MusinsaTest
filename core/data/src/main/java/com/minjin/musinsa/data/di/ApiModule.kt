package com.minjin.musinsa.data.di

import com.minjin.musinsa.data.service.InterviewApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object ApiModule {

    @Provides
    @Singleton
    fun provideInterviewApi(
        retrofit: Retrofit
    ): InterviewApi = retrofit.create(InterviewApi::class.java)
}