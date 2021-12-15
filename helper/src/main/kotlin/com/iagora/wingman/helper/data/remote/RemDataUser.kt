package com.iagora.wingman.helper.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class RemDataUser(
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("id_user") var idUser: String? = null,
    @SerializedName("img_profile") var imgProfile: String? = null,
    @SerializedName("phone_number") var phoneNumber: String? = null,
)