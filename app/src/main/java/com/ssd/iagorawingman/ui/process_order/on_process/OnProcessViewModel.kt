package com.ssd.iagorawingman.ui.process_order.on_process

import androidx.lifecycle.ViewModel
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnProcessViewModel(
    private val orderUseCase: ProcessOrderUseCase
) : ViewModel() {
    fun vmGetWaitingList() = orderUseCase.getAllListWaiting()


    private val _totalWaitingList: MutableStateFlow<Int> = MutableStateFlow(0)
    val totalWaitingList: StateFlow<Int> = _totalWaitingList

    fun setTotalWaitingList(total: Int) {
        _totalWaitingList.value = total
    }

}