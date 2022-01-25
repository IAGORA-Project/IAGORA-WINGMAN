package com.iagora.wingman.app.auth.data.remote.request


import com.google.gson.annotations.SerializedName

 data class LoginReq(
     val phoneNumber: OtpReq,
     @SerializedName("password", alternate = ["otp"]) val otp: String,
 )