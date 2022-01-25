package com.iagora.wingman.app.auth.data.remote.response


import com.google.gson.annotations.SerializedName

data class ResLogin(
    @SerializedName("status") val status: Boolean,
    @SerializedName("success") val success: Success
) {
    data class Success(
        @SerializedName("token") val token: String
    )
}