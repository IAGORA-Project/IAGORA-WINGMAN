package com.iagora.wingman.helper.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemProduct(
    @SerializedName("bergain_price") var bargainPrice: Long? = null,
    @SerializedName("id_product") var idProduct: String? = null,
    @SerializedName("product_name") var productName: String? = null,
    @SerializedName("qty") var qty: Int? = null,
    @SerializedName("satuan") var unit: String? = null,
    @SerializedName("uom") var uom: Int? = null,
) : Parcelable