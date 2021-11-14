package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.utils.FlowProcessOrder
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmedDetailViewModel(
    private val orderUseCase: ProcessOrderUseCase,
) : ViewModel() {

    private val _vmGetDetailConfirmed: MutableSharedFlow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetDetailConfirmed =
        _vmGetDetailConfirmed.distinctUntilChanged { old, new ->
            old.data?.success?.idTransaction != new.data?.success?.idTransaction
        }.buffer(1, BufferOverflow.DROP_OLDEST)

    fun setIdTransaction(idTransaction: String) {
        viewModelScope.launch {
            orderUseCase.getDetailListWaiting(idTransaction, FlowProcessOrder.CONFIRMATION.name)
                .collectLatest {res->
                    _vmGetDetailConfirmed.emit(res)
                }
        }
    }
}