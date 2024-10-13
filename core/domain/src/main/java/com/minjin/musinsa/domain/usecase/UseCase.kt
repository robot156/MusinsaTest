package com.minjin.musinsa.domain.usecase

import com.minjin.musinsa.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, out Type>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(params: Params): Result<Type> {
        return try {
            withContext(coroutineDispatcher) {
                execute(params).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: Params): Type
}