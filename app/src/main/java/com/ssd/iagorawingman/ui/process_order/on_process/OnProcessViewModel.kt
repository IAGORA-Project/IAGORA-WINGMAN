package com.ssd.iagorawingman.ui.process_order.on_process

import androidx.lifecycle.ViewModel
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.usecase.OnProcessUseCase
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow

class OnProcessViewModel(
    private val useCase: OnProcessUseCase
) : ViewModel() {
    private val _totalWaitingList: MutableStateFlow<Int> = MutableStateFlow(0)
    val totalWaitingList: StateFlow<Int> = _totalWaitingList

    fun vmGetWaitingList() = useCase.getAllListWaiting()

    fun setTotalWaitingList(total:Int){
        _totalWaitingList.value = total
    }
}