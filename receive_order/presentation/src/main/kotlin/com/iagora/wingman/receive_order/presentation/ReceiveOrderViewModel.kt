package com.iagora.wingman.receive_order.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.core.util.Event
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.UiText
import com.iagora.wingman.receive_order.domain.models.body.ReceiveOrder
import com.iagora.wingman.receive_order.domain.usecase.IAcceptOrder
import com.iagora.wingman.receive_order.domain.usecase.ICancelOrder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class ReceiveOrderViewModel(
    private val cancelOrder: ICancelOrder,
    private val acceptOrder: IAcceptOrder
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFLow = _eventFlow.asSharedFlow()


    fun cancel(request: ReceiveOrder) = viewModelScope.launch {
        _eventFlow.emit(ReceiveOrderEvent.LoadingProcess(true))
        val response = cancelOrder(request)
        _eventFlow.emit(ReceiveOrderEvent.LoadingProcess(false))
        when (response) {
            is Resource.Success -> {
                _eventFlow.emit(ReceiveOrderEvent.MoveToHome)
            }
            is Resource.Error -> {
                _eventFlow.emit(
                    UiEvent.CreateMessage(
                        response.uiText ?: UiText.unknownError()
                    )
                )
            }
        }
    }

    fun accept(request: ReceiveOrder) = viewModelScope.launch {
        _eventFlow.emit(ReceiveOrderEvent.LoadingProcess(true))
        val response = acceptOrder(request)
        _eventFlow.emit(ReceiveOrderEvent.LoadingProcess(false))
        when (response) {
            is Resource.Success -> {
                _eventFlow.emit(ReceiveOrderEvent.MoveToProcessOrder)
            }
            is Resource.Error -> {
                _eventFlow.emit(
                    UiEvent.CreateMessage(
                        response.uiText ?: UiText.unknownError()
                    )
                )
            }
        }
    }

}