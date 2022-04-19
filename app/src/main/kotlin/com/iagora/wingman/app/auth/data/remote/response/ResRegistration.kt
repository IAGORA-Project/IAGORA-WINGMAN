package com.iagora.wingman.app.auth.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResRegistration(
    @SerializedName("wingmanDetail")
    val wingmanDetail: WingmanDetail,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("no_hp")
    val phone: String? = null,

    @SerializedName("createdAt")
    val createdAt: String? = null,

    @SerializedName("updatedAt")
    val updatedAt: String? = null

)

data class WingmanDetail(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("avatar")
    val avatar: String? = null
)