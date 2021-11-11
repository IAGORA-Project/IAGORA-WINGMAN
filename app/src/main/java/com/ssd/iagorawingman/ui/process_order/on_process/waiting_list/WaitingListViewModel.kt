package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WaitingListViewModel(
    private val orderUseCase: ProcessOrderUseCase
) : ViewModel() {

    private val _vmGetWaitingList: MutableSharedFlow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetWaitingList = _vmGetWaitingList.asSharedFlow()

    fun setTypeWaiting(typeWaiting: String) {
        viewModelScope.launch {
            orderUseCase.getAllListWaiting(typeWaiting).collect { res ->
                _vmGetWaitingList.emit(res)
            }
        }
    }

}