package com.iagora.wingman.app.auth.data.remote.request

import com.google.gson.annotations.SerializedName

data class VerifyOtpReq(
    @SerializedName("no_hp") var phoneNumber: String,
    @SerializedName("otp_code") val otp: String
)
