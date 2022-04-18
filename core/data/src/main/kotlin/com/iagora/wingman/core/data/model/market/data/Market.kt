package com.iagora.wingman.core.data.model.market.data

import com.google.gson.annotations.SerializedName

data class Market(
    val name: String,
    val address: String,
    val city: String,
    val isAccept: Boolean,
    @SerializedName("product")
    val productId: ArrayList<String>
)

data class MarketWithProduct(
    val name: String,
    val address: String,
    val city: String,
    val isAccept: Boolean,
    @SerializedName("products")
    val product: ArrayList<Product>
)
