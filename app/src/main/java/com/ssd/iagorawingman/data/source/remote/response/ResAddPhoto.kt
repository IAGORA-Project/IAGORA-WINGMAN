package com.ssd.iagorawingman.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResAddPhoto(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("success")
    val success: Success? = null
) {
    data class Success(
        @SerializedName("_id")
        val id: String? = null,
        @SerializedName("img")
        val img: ArrayList<String>? = null,
        @SerializedName("__v")
        val v: Int? = null
    )
}