package com.iagora.wingman.app.auth.data.remote.response


import com.google.gson.annotations.SerializedName

data class ResLogin(
    @SerializedName("_id") val id: String?,
    @SerializedName("income") val income: Int?,
    @SerializedName("ktp") val ktp: String?,
    @SerializedName("nama") val name: String?,
    @SerializedName("today_order") val todayOrder: Int?,
)