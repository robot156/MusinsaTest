package com.minjin.musinsa.list

import androidx.lifecycle.ViewModel
import com.minjin.musinsa.domain.usecase.list.GetInterviewListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getInterviewListUseCase: GetInterviewListUseCase
) : ViewModel() {



}