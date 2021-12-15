package com.iagora.wingman.core.source.remote.body


import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("phone_number") var phoneNumber: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("device_token") var deviceToken: String? = null,
)