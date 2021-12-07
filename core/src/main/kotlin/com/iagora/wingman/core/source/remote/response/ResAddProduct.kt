package com.iagora.wingman.core.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResAddProduct(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: Success? = null
) {
    data class Success(
        @SerializedName("message") var message: String? = null
    )
}