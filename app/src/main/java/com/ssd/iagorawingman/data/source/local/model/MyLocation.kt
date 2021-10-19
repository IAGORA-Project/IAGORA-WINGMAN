package com.ssd.iagorawingman.data.source.local.model


import com.google.gson.annotations.SerializedName

data class MyLocation(
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("longitude") val longitude: Double? = null
)