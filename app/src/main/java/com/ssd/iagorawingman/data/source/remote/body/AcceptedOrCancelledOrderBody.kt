package com.ssd.iagorawingman.data.source.remote.body


import com.google.gson.annotations.SerializedName

data class AcceptedOrCancelledOrderBody(
    @SerializedName("id_cart") var idCart: String? = null,
    @SerializedName("id_pasar") var idPasar: String? = null,
    @SerializedName("id_user") var idUser: String? = null,
    @SerializedName("index") var index: Int? = null,
    @SerializedName("list_product") var listProduct: List<Product?>? = null
) {
    data class Product(
        @SerializedName("bergain_price") var bergainPrice: Int? = null,
        @SerializedName("id_product") var idProduct: String? = null,
        @SerializedName("qty") var qty: Int? = null,
        @SerializedName("satuan") var satuan: String? = null,
        @SerializedName("uom") var uom: Int? = null
    )
}