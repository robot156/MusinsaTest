package com.minjin.musinsa.data.di

import com.minjin.musinsa.data.datasource.ListRepositoryImpl
import com.minjin.musinsa.data.datasource.remote.ListRemoteDatasource
import com.minjin.musinsa.data.datasource.remote.ListRemoteDatasourceImpl
import com.minjin.musinsa.domain.usecase.list.ListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindListRepository(listRepositoryImpl: ListRepositoryImpl): ListRepository

    @Singleton
    @Binds
    abstract fun bindListRemoteDatasource(listRemoteDatasourceImpl: ListRemoteDatasourceImpl): ListRemoteDatasource

}