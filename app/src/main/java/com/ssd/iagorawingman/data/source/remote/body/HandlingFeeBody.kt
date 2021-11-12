package com.ssd.iagorawingman.data.source.remote.body


import com.google.gson.annotations.SerializedName

data class HandlingFeeBody(
    @SerializedName("handling_fee") var handlingFee: Long?
)