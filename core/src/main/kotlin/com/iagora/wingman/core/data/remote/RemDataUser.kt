package com.iagora.wingman.core.data.remote

import com.google.gson.annotations.SerializedName

data class RemDataUser(
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("id_user") var idUser: String? = null,
    @SerializedName("img_profile") var imgProfile: String? = null,
    @SerializedName("phone_number") var phoneNumber: String? = null,
)