package com.ssd.iagorawingman.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResGetWingmanInfo(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: Success? = null
) {
    data class Success(
        @SerializedName("balance") var balance: Int? = null,
        @SerializedName("email") var email: String? = null,
        @SerializedName("full_name") var fullName: String? = null,
        @SerializedName("id_kol") var idKol: String? = null,
        @SerializedName("img_profile") var imgProfile: String? = null,
        @SerializedName("phone_number") var phoneNumber: String? = null
    )
}