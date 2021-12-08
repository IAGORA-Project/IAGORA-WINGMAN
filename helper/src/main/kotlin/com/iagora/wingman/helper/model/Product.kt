package com.iagora.wingman.helper.model
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Product(
    val bargainPrice: Long,
    val idProduct: String,
    val productName: String,
    var qty: Int,
    val unit: String,
    val uom: Int,
): Parcelable