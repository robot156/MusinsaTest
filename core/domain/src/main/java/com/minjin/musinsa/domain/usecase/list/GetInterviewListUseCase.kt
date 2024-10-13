package com.minjin.musinsa.domain.usecase.list

import com.minjin.musinsa.domain.usecase.FlowUseCase
import com.minjin.musinsa.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetInterviewListUseCase @Inject constructor(
    private val listRepository: ListRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Unit>(ioDispatcher) {

    override fun execute(params: Unit) = flow {
        emit(listRepository.getInterviewList())
    }
}