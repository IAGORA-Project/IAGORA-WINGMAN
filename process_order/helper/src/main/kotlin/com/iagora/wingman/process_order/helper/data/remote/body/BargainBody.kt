package com.iagora.wingman.process_order.helper.data.remote.body


import com.google.gson.annotations.SerializedName

data class BargainBody(
    @SerializedName("id_product") val idProduct: String,
    @SerializedName("id_transaction") val idTransaction: String,
    @SerializedName("new_bergain") val newBargain: Int,
    @SerializedName("uom") val uom: Int,
)