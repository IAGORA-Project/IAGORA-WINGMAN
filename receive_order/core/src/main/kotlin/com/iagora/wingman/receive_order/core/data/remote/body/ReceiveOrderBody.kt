package com.iagora.wingman.receive_order.core.data.remote.body


import com.google.gson.annotations.SerializedName
import com.iagora.wingman.core.data.remote.RemDataUser
import com.iagora.wingman.core.data.remote.RemProduct


data class ReceiveOrderBody(
    @SerializedName("data_user") val dataUser: RemDataUser,
    @SerializedName("id_cart") val idCart: String,
    @SerializedName("id_pasar") val idMarket: String,
    @SerializedName("index") val index: Int,
    @SerializedName("list_product") val listProduct: List<RemProduct>,
)
