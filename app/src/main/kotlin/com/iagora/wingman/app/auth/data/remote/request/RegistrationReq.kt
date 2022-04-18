package com.iagora.wingman.app.auth.data.remote.request

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.Part

data class RegistrationReq (
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("city")
    val city: String? = null
)

data class RegistWingmanDetailReq(
    val nameRekn: String,
    val noRekn: String,
    val bank: String
)
