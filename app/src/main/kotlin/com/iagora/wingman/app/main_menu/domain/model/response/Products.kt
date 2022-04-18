package com.iagora.wingman.app.main_menu.domain.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class RestAllCart(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("result")
    val result: Cart
)

data class Cart(
    @SerializedName("products")
    val product : ArrayList<Product>
)

data class Product(
    @SerializedName("product")
    val product_item: ProductItem,

    @SerializedName("quantity")
    val quantity: Int,

    @SerializedName("subTotal")
    val subtotal: String)

@Parcelize
data class ProductItem(
    @SerializedName("product_name")
    val name: String,
    @SerializedName("product_category")
    val category: String,

    @SerializedName("product_grade")
    val grade:String,

    @SerializedName("product_image")
    val image: String,

    @SerializedName("product_price")
    val price: String,
    @SerializedName("product_oum")
    val oum: String) : Parcelable


data class Products(
    val product : ArrayList<Product>
)
