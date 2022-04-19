package com.iagora.wingman.app.auth.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResVerifyOtp(
    @SerializedName("wingmanId")
    val userId: String? = null,
    @SerializedName("refreshToken")
    val refreshToken: String? = null
)
