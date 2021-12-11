package com.iagora.wingman.market.helper.model.response

data class ListMarket(
    val status: Int,
    val success: List<Success>,
) {
    data class Success(
        val idMarket: String,
        val infoAddress: InfoAddress,
        val lat: String,
        val long: String,
        val marketName: String,
    ) {
        data class InfoAddress(
            val address: String,
            val city: String,
            val zip: String,
            val main: Boolean,
            val province: String,
        )
    }
}