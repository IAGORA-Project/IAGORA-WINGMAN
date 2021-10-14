package com.ssd.iagorawingman.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResAddPhoto(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: ArrayList<Success>? = null
) {
    data class Success(
        @SerializedName("destination") var destination: String? = null,
        @SerializedName("encoding") var encoding: String? = null,
        @SerializedName("fieldname") var fieldname: String? = null,
        @SerializedName("filename") var filename: String? = null,
        @SerializedName("mimetype") var mimetype: String? = null,
        @SerializedName("originalname") var originalname: String? = null,
        @SerializedName("path") var path: String? = null,
        @SerializedName("size") var size: Int? = null
    )
}