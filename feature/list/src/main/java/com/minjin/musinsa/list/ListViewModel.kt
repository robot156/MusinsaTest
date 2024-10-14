package com.minjin.musinsa.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minjin.musinsa.domain.usecase.list.GetInterviewListUseCase
import com.minjin.musinsa.domain.util.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getInterviewListUseCase: GetInterviewListUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getInterviewListUseCase(Unit).asResult().collect {

            }
        }
    }
}