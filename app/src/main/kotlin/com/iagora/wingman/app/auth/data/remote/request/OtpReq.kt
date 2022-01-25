package com.iagora.wingman.app.auth.data.remote.request

import com.google.gson.annotations.SerializedName

data class OtpReq(
    @SerializedName("hp", alternate = ["username"]) var phoneNumber: String,
)
