package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.utils.FlowProcessOrder
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmationViewModel(
    private val orderUseCase: ProcessOrderUseCase,
) : ViewModel() {

    private val _vmGetConfirmationList: MutableSharedFlow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        MutableSharedFlow(1, 0, BufferOverflow.DROP_LATEST)
    val vmGetConfirmationList = _vmGetConfirmationList.distinctUntilChanged()

    private val _vmCountSizeConfirmationList: MutableSharedFlow<Int> =
        MutableSharedFlow()
    val vmCountSizeConfirmationList = _vmCountSizeConfirmationList.distinctUntilChanged()

    fun initViewModelConfirmation() {
        viewModelScope.launch {
            orderUseCase.getAllListWaiting(FlowProcessOrder.WAITING_CONFIRMATION.name)
                .collectLatest { res ->
                    _vmGetConfirmationList.emit(res)
                    _vmCountSizeConfirmationList.emit(res.data?.success?.size ?: 0)
                }
        }

    }
}