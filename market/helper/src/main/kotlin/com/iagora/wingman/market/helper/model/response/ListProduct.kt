package com.iagora.wingman.market.helper.model.response


data class ListProduct(
    val status: Int,
    val success: List<Success>,
) {
    data class Success(
        val idProduct: String,
        val idMarket: String,
        val productName: String,
        val img: String,
        val price: Long,
        val desc: String,
        val characteristics: String,
    )
}