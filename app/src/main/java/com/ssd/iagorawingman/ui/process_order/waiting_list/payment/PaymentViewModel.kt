package com.ssd.iagorawingman.ui.process_order.waiting_list.payment

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

class PaymentViewModel(
    private val orderUseCase: ProcessOrderUseCase,
) : ViewModel() {

    private val _vmGetPaymentList: MutableSharedFlow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        MutableSharedFlow(1, 1, BufferOverflow.DROP_OLDEST)
    val vmGetPaymentList = _vmGetPaymentList.distinctUntilChanged()

    private val _vmCountSizePaymentList: MutableSharedFlow<Int> = MutableSharedFlow()
    val vmCountSizePaymentList = _vmCountSizePaymentList.distinctUntilChanged()

    fun initViewModelPayment() = viewModelScope.launch {
        orderUseCase.getAllListWaiting(FlowProcessOrder.WAITING_PAYMENT.name).collectLatest { res ->
            _vmGetPaymentList.emit(res)
            _vmCountSizePaymentList.emit(res.data?.success?.size ?: 0)

        }
    }
}