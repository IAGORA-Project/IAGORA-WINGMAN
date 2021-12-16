package com.iagora.wingman.core.data.remote

import com.google.gson.annotations.SerializedName

data class RemAddressUser(
    @SerializedName("details") var details: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("note") var note: String? = null,
    @SerializedName("phone_number") var phoneNumber: String? = null,
)