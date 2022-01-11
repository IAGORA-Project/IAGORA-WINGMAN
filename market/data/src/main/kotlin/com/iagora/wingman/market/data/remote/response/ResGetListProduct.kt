package com.iagora.wingman.market.data.remote.response


import com.google.gson.annotations.SerializedName

data class ResGetListProduct(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: ArrayList<Success>? = null
) {
    data class Success(
        @SerializedName("id_product") var idProduct: String? = null,
        @SerializedName("id_pasar") var idMarket: String? = null,
        @SerializedName("product_name") var productName: String? = null,
        @SerializedName("img") var img: String? = null,
        @SerializedName("price") var price: Long? = null,
        @SerializedName("desc") var desc: String? = null,
        @SerializedName("characteristics") var characteristics: String? = null
    )
}