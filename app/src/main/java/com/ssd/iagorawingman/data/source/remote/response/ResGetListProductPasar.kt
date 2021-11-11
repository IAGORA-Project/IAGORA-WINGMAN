package com.ssd.iagorawingman.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResGetListProductPasar(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: ArrayList<Success>? = null
) {
    data class Success(
        @SerializedName("id_product") var idProduct: String? = null,
        @SerializedName("id_pasar") var idPasar: String? = null,
        @SerializedName("product_name") var productName: String? = null,
        @SerializedName("img") var img: String? = null,
        @SerializedName("price") var price: Int? = null,
        @SerializedName("desc") var desc: String? = null,
        @SerializedName("characteristics") var characteristics: String? = null
    )
}