package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail.confirmed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.utils.Constants
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmedViewModel(
    private val orderUseCase: ProcessOrderUseCase
) : ViewModel() {

    private val _vmGetDetailConfirmed: MutableSharedFlow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetDetailConfirmed =
        _vmGetDetailConfirmed.distinctUntilChanged { old, new ->
            old.data?.success?.idTransaction != new.data?.success?.idTransaction
        }.buffer(1, BufferOverflow.DROP_OLDEST)


    fun setIdTransaction(idTransaction: String, typeWaiting:String) {
        viewModelScope.launch {
            orderUseCase.getDetailListWaiting(idTransaction, typeWaiting)
                .collectLatest {
                    _vmGetDetailConfirmed.emit(it)
                }
        }
    }
}