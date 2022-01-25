package com.iagora.wingman.core.data.remote.response

data class BasicResponse(
    val status: Int,
    val message: String
){
    fun isSuccess() = status in 200..206
}