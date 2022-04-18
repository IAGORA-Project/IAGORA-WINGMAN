package com.iagora.wingman.market.helper.model.body


data class Variant(
    val idList: Int,
    val avgPrice: Long,
    val highestPrice: Long,
    val lowPrice: Long,
    val unit: String,
    val uom: Long,
)
