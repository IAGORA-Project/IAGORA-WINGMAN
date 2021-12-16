package com.iagora.wingman.process_order.core.data.remote.body


import com.google.gson.annotations.SerializedName

data class HandlingFeeBody(
    @SerializedName("handling_fee") var price: Long?,
)