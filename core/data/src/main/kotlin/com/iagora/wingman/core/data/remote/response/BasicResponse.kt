package com.iagora.wingman.core.data.remote.response

typealias SimpleResponse = BasicResponse<Unit>

data class BasicResponse<T>(
    val status: Int?,
    val message: String?,
    val result: T? = null
) {
    fun isSuccess() = status in 200..206
}