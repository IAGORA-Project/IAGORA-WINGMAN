package com.iagora.wingman.market.helper.model.response


data class AddProductFeedBack(
    val status: Int,
    val success: Success,
) {
    data class Success(
        val message: String,
    )
}