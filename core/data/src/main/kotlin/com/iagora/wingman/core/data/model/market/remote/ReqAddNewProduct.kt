package com.iagora.wingman.core.data.model.market.remote

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class ReqAddNewProduct(
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_category")
    val productCategory: String,
    @SerializedName("product_grade")
    val productGrade: String,
    @SerializedName("product_image")
    val productImage: MultipartBody.Part,
    @SerializedName("product_price")
    val productPrice: String,
    @SerializedName("product_uom")
    val productOum: String,
    @SerializedName("marketId")
    val marketId: String,
)