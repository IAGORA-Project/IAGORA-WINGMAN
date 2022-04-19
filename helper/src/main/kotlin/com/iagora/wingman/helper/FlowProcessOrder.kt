package com.iagora.wingman.helper

enum class FlowProcessOrder {
    WAITING_CONFIRMATION,
    CONFIRMATION,
    WAITING_PAYMENT,
    PAID,
    SENT,
    FINISH,
}