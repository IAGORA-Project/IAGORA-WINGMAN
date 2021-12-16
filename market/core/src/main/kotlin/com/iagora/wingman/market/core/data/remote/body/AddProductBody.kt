package com.iagora.wingman.market.core.data.remote.body


import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class AddProductBody(
    @SerializedName("id_pasar") val idMarket: String,
    @SerializedName("img") val image: List<MultipartBody.Part>,
    @SerializedName("nama_produk") val productName: String,
    @SerializedName("desc") var desc: String,
    @SerializedName("item_categories") val itemCategories: String,
    @SerializedName("item_type") val itemType: String,
    @SerializedName("satuan") val unit: String,
    @SerializedName("variant") val variant: List<Variant>
) {
    data class Variant(
        @SerializedName("id_list") val idList: Int,
        @SerializedName("avg_price") val avgPrice: Int,
        @SerializedName("highest_price") val highestPrice: Int,
        @SerializedName("low_price") val lowPrice: Int,
        @SerializedName("satuan") val unit: String,
        @SerializedName("uom") val uom: Int
    )
}