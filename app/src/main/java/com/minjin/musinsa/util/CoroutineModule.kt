package com.minjin.musinsa.util

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CoroutineModule {

//    @DefaultDispatcher
//    @Provides
//    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
//
//    @IoDispatcher
//    @Provides
//    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
//
//    @MainDispatcher
//    @Provides
//    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}