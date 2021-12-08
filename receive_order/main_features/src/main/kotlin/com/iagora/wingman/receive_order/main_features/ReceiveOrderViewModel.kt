package com.iagora.wingman.receive_order.main_features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.receive_order.core.domain.usecase.ReceiveOrderUseCase
import com.iagora.wingman.receive_order.core.model.body.ReceiveOrder
import com.iagora.wingman.receive_order.core.model.response.AcceptedOrder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ReceiveOrderViewModel(
    private val receiveOrderUseCase: ReceiveOrderUseCase,
) : ViewModel() {
    private val _vmFeedbackActionOrder: MutableSharedFlow<Resource<AcceptedOrder>> =
        MutableSharedFlow()
    val vmFeedbackActionOrder = _vmFeedbackActionOrder.asSharedFlow()

    fun postActionOrder(action: String, body: ReceiveOrder) = viewModelScope.launch {
        receiveOrderUseCase.postActionOrder(action, body).collectLatest {
            _vmFeedbackActionOrder.emit(it)
        }

    }
}