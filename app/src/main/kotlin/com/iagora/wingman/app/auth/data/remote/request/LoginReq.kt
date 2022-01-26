package com.iagora.wingman.app.auth.data.remote.request


import com.google.gson.annotations.SerializedName

data class LoginReq(
    @SerializedName("username") var phoneNumber: String,
    @SerializedName("password") val otp: String,
)