package com.iagora.wingman.receive_order.features.main_features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.receive_order.core.domain.usecase.ReceiveOrderUseCase
import com.iagora.wingman.receive_order.helper.TypeAction
import com.iagora.wingman.receive_order.helper.model.body.ReceiveOrder
import com.iagora.wingman.receive_order.helper.model.response.AcceptedOrder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ReceiveOrderViewModel(
    private val receiveOrderUseCase: ReceiveOrderUseCase,
) : ViewModel() {
    private val _vmFeedbackAcceptedOrder: MutableSharedFlow<Resource<AcceptedOrder>> =
        MutableSharedFlow()
    val vmFeedbackAcceptedOrder = _vmFeedbackAcceptedOrder.asSharedFlow()

    private val _vmFeedbackCanceledOrder: MutableSharedFlow<Resource<AcceptedOrder>> =
        MutableSharedFlow()
    val vmFeedbackCanceledOrder = _vmFeedbackCanceledOrder.asSharedFlow()

    fun postAcceptedOrder(body: ReceiveOrder) = viewModelScope.launch {
        receiveOrderUseCase.postActionOrder(TypeAction.accepted.name, body).collectLatest {
            _vmFeedbackAcceptedOrder.emit(it)
        }
    }

    fun postCanceledOrder(body: ReceiveOrder) = viewModelScope.launch {
        receiveOrderUseCase.postActionOrder(TypeAction.canceled.name, body).collectLatest {
            _vmFeedbackCanceledOrder.emit(it)
        }
    }
}