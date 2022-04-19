package com.iagora.wingman.core.data.model.market.data

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("name")
    val name: String,
    @SerializedName("market")
    val marketId: ArrayList<String>
)
