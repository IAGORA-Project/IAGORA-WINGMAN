package com.iagora.wingman.app.main_menu.data.remote.response


import com.google.gson.annotations.SerializedName

data class ResWingmanInfo(
    @SerializedName("status") val status: Int,
    @SerializedName("success") val success: Success
) {
    data class Success(
        @SerializedName("balance") val balance: Int? = null,
        @SerializedName("email") val email: String? = null,
        @SerializedName("full_name") val fullName: String? = null,
        @SerializedName("id_kol") val idKol: String,
        @SerializedName("img_profile") val imgProfile: String? = null,
        @SerializedName("phone_number") val phoneNumber: String? = null,
        @SerializedName("active") val statusActive: Boolean = false
    )
}