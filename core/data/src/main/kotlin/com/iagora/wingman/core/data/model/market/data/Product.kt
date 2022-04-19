package com.iagora.wingman.core.data.model.market.data

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val idProduct: String,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_category")
    val productCategory: ProductCategory,
    @SerializedName("product_grade")
    val productGrade: String,
    @SerializedName("product_image")
    val productImage: String,
    @SerializedName("product_price")
    val productPrice: String,
    @SerializedName("product_uom")
    val productOum: String,
    @SerializedName("market")
    val market: String,
    @SerializedName("isAccept")
    val isAccept: Boolean
)

data class ProductWithMarket(
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_category")
    val productCategory: String,
    @SerializedName("product_grade")
    val productGrade: String,
    @SerializedName("product_image")
    val productImage: String,
    @SerializedName("product_price")
    val productPrice: String,
    @SerializedName("product_uom")
    val productOum: String,
    @SerializedName("market")
    val market: String,
    @SerializedName("isAccept")
    val isAccept: Boolean
)

data class ProductCategory(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String
)
