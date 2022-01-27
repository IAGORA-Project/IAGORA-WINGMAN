package com.iagora.wingman.app.auth.data.remote.request


import com.google.gson.annotations.SerializedName

data class LoginReq(
    @SerializedName("no_hp") var phoneNumber: String,
    @SerializedName("otp") val otp: String,
)