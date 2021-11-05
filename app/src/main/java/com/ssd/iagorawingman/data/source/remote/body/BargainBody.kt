package com.ssd.iagorawingman.data.source.remote.body


import com.google.gson.annotations.SerializedName

data class BargainBody(
    @SerializedName("id_product") var idProduct: String? = null,
    @SerializedName("id_transaction") var idTransaction: String? = null,
    @SerializedName("new_bergain") var newBargain: Int? = null,
    @SerializedName("uom") var uom: Int? = null
)