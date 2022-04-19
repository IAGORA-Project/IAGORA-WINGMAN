package com.iagora.wingman.market.data.remote.response


import com.google.gson.annotations.SerializedName

data class ResAddProductFeedback(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: Success? = null
) {
    data class Success(
        @SerializedName("message") var message: String? = null
    )
}