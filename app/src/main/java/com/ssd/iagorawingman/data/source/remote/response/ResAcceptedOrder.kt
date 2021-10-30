package com.ssd.iagorawingman.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResAcceptedOrder(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: Success? = null
) {
    data class Success(
        @SerializedName("data") var `data`: Data? = null,
        @SerializedName("message") var message: String? = null
    ) {
        data class Data(
            @SerializedName("acknowledged") var acknowledged: Boolean? = null,
            @SerializedName("matchedCount") var matchedCount: Int? = null,
            @SerializedName("modifiedCount") var modifiedCount: Int? = null,
            @SerializedName("upsertedCount") var upsertedCount: Int? = null,
            @SerializedName("upsertedId") var upsertedId: Any? = null
        )
    }
}