package com.iagora.wingman.app.auth.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResAccessToken(
    @SerializedName("wingmanId")
    val userId: String? = null,
    @SerializedName("accessToken")
    val accessToken: String? = null
)
