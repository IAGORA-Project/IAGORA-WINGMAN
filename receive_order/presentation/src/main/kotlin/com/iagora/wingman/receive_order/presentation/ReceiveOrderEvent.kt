package com.iagora.wingman.receive_order.presentation

import com.iagora.wingman.core.util.Event

sealed class ReceiveOrderEvent : Event() {
    object MoveToProcessOrder : ReceiveOrderEvent()
    object MoveToHome : ReceiveOrderEvent()
    data class LoadingProcess(val isLoading: Boolean) : ReceiveOrderEvent()
}