package com.ssd.iagorawingman.data.source.local.model


import com.google.gson.annotations.SerializedName

data class MyLocation(
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null
)