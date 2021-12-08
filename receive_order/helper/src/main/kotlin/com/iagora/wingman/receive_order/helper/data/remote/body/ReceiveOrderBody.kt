package com.iagora.wingman.receive_order.helper.data.remote.body


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.iagora.wingman.helper.data.remote.RemDataUser
import com.iagora.wingman.helper.data.remote.RemProduct
import kotlinx.parcelize.Parcelize


data class ReceiveOrderBody(
    @SerializedName("data_user") val dataUser: RemDataUser,
    @SerializedName("id_cart") val idCart: String,
    @SerializedName("id_pasar") val idMarket: String,
    @SerializedName("index") val index: Int,
    @SerializedName("list_product") val listProduct: List<RemProduct>,
)
