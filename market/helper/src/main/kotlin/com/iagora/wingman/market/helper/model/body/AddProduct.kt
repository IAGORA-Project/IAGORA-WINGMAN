package com.iagora.wingman.market.helper.model.body


import okhttp3.MultipartBody

data class AddProduct(
    val idMarket: String,
    val image: List<MultipartBody.Part>,
    val productName: String,
    var desc: String,
    val itemCategories: String,
    val itemType: String,
    val unit: String,
    val variant: List<Variant>,
) {
    data class Variant(
        val idList: Int,
        val avgPrice: Int,
        val highestPrice: Int,
        val lowPrice: Int,
        val unit: String,
        val uom: Int,
    )
}