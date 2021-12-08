package com.iagora.wingman.process_order.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.process_order.core.domain.usecase.ProcessOrderUseCase
import com.iagora.wingman.process_order.helper.FlowProcessOrder
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val processOrderUseCase: ProcessOrderUseCase,
) : ViewModel() {

    private val _vmGetPaymentList: MutableSharedFlow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)
    val vmGetPaymentList = _vmGetPaymentList.distinctUntilChanged()

    private val _vmCountSizePaymentList: MutableSharedFlow<Int> = MutableSharedFlow()
    val vmCountSizePaymentList = _vmCountSizePaymentList.distinctUntilChanged()

    init {
        initViewModelPayment()
    }

    fun initViewModelPayment() = viewModelScope.launch {
        processOrderUseCase.getAllListWaiting(FlowProcessOrder.WAITING_PAYMENT.name).collectLatest { res ->
            _vmGetPaymentList.emit(res)
            _vmCountSizePaymentList.emit(res.data?.success?.size ?: 0)

        }
    }
}