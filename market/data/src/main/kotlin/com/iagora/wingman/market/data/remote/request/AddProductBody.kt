package com.iagora.wingman.market.data.remote.request


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
    @SerializedName("variant") val listVariantBody: List<VariantBody>
)