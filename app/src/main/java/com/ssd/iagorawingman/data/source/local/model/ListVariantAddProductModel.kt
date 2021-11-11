package com.ssd.iagorawingman.data.source.local.model


import com.google.gson.annotations.SerializedName

data class ListVariantAddProductModel(
    @SerializedName("list_variant") var listVariant: ArrayList<Variant>? = null
) {
    data class Variant(
        @SerializedName("id_list") var idList: Int? = null,
        @SerializedName("price") var price: String? = null,
        @SerializedName("satuan") var satuan: String? = null,
        @SerializedName("variant") var variant: String? = null
    )
}