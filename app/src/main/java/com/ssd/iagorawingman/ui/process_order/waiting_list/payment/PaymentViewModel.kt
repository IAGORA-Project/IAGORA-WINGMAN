package com.ssd.iagorawingman.ui.process_order.waiting_list.payment

import androidx.lifecycle.ViewModel
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.utils.FlowProcessOrder

class PaymentViewModel(
    private val orderUseCase: ProcessOrderUseCase,
) : ViewModel() {
    val vmGetPaymentList =
        orderUseCase.getAllListWaiting(FlowProcessOrder.WAITING_PAYMENT.name)
}