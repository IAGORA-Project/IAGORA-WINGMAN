package com.iagora.wingman.receive_order.core.data.remote.body


import com.google.gson.annotations.SerializedName
import com.iagora.wingman.core.data.remote.dto.DataUserDto
import com.iagora.wingman.core.data.remote.dto.ProductDto


data class ReceiveOrderBody(
    @SerializedName("data_user") val dataUser: DataUserDto,
    @SerializedName("id_cart") val idCart: String,
    @SerializedName("id_pasar") val idMarket: String,
    @SerializedName("index") val index: Int,
    @SerializedName("list_product") val listProduct: List<ProductDto>,
)
