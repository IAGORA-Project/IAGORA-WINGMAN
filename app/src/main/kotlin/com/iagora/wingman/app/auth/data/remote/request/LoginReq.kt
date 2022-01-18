package com.iagora.wingman.app.auth.data.remote.request


import com.google.gson.annotations.SerializedName

 data class LoginReq(
    @SerializedName("phone_number") var phoneNumber: String,
    @SerializedName("password") var password: String,
    @SerializedName("device_token") var deviceToken: String,
)