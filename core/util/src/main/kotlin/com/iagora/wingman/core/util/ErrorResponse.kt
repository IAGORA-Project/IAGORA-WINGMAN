package com.iagora.wingman.core.util

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("message")
    @Expose
    val message: String? = null,

    @SerializedName("status")
    @Expose
    val code: Int? = null,

    @SerializedName("result")
    val result: ArrayList<result>? = null
)


data class result(
    @SerializedName("msg")
    @Expose
    val msg: String? = null,
)

