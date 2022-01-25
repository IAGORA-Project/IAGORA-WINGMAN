package com.iagora.wingman.process_order.helper.model.body


data class Bargain(
    val idProduct: String,
    val idTransaction: String,
    val newBargain: Int,
    val uom: Int,
)