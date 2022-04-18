package com.iagora.wingman.market.data.remote.request

import com.google.gson.annotations.SerializedName


data class VariantBody(
    @SerializedName("id_list") val idList: Int,
    @SerializedName("avg_price") val avgPrice: Long,
    @SerializedName("highest_price") val highestPrice: Long,
    @SerializedName("low_price") val lowPrice: Long,
    @SerializedName("satuan") val unit: String,
    @SerializedName("uom") val uom: Long
)

