package com.iagora.wingman.app.auth.data.remote.request

import com.google.gson.annotations.SerializedName

data class OtpReq(
    @SerializedName("no_hp") var phoneNumber: String,
)
