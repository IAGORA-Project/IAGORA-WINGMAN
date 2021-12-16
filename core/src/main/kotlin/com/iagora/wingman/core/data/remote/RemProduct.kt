package com.iagora.wingman.core.data.remote

import com.google.gson.annotations.SerializedName


data class RemProduct(
    @SerializedName("bergain_price") var bargainPrice: Long? = null,
    @SerializedName("id_product") var idProduct: String? = null,
    @SerializedName("product_name", alternate = ["name_product"]) var productName: String? = null,
    @SerializedName("qty") var qty: Int? = null,
    @SerializedName("satuan") var unit: String? = null,
    @SerializedName("uom") var uom: Int? = null,
)