package com.iagora.wingman.receive_order.data.remote.body


import com.iagora.wingman.core.data.remote.dto.DataUserDto
import com.iagora.wingman.core.data.remote.dto.ProductDto


data class ReceiveOrderBody(
    val dataUser: DataUserDto,
    val idCart: String,
    val idMarket: String,
    val index: Int,
    val listProduct: List<ProductDto>,
)
