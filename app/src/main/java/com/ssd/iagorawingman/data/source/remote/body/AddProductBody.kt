package com.ssd.iagorawingman.data.source.remote.body


import com.google.gson.annotations.SerializedName
import com.ssd.iagorawingman.data.source.local.model.ImageModel
import okhttp3.MultipartBody

data class AddProductBody(
    @SerializedName("id_pasar") var idPasar: String? = null,
    @SerializedName("img") var image: ArrayList<MultipartBody.Part> = ArrayList(),
    @SerializedName("nama_produk") var namaProduk: String? = null,
    @SerializedName("desc") var desc: String? = null,
    @SerializedName("item_categories") var itemCategories: String? = null,
    @SerializedName("item_type") var itemType: String? = null,
    @SerializedName("satuan") var satuan: String? = null,
    @SerializedName("variant") var variant: ArrayList<Variant> = ArrayList()
) {
    data class Variant(
        @SerializedName("id_list") var idList: Int? = null,
        @SerializedName("avg_price") var avgPrice: Int? = null,
        @SerializedName("highest_price") var highestPrice: Int? = null,
        @SerializedName("low_price") var lowPrice: Int? = null,
        @SerializedName("satuan") var satuan: String? = null,
        @SerializedName("uom") var uom: Int? = null
    )
}