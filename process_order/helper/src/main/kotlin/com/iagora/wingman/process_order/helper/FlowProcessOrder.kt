package com.iagora.wingman.process_order.helper

enum class FlowProcessOrder {
    WAITING_CONFIRMATION,
    CONFIRMATION,
    WAITING_PAYMENT,
    PAID,
    SENT,
    FINISH,
}