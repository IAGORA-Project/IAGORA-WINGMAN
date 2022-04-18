package com.iagora.wingman.receive_order.domain.models.body


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.iagora.wingman.core.domain.model.dto.DataUser
import com.iagora.wingman.core.domain.model.dto.Product
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReceiveOrder(
    @SerializedName("data_user") val dataUser: DataUser,
    @SerializedName("id_cart") val idCart: String,
    @SerializedName("id_pasar") val idMarket: String,
    @SerializedName("index") val index: Int,
    @SerializedName("list_product") val listProduct: List<Product>,
) : Parcelable