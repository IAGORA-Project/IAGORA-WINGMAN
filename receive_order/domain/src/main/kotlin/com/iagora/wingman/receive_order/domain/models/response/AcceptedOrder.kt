package com.iagora.wingman.receive_order.domain.models.response

class AcceptedOrder(
    val status: Int,
    val success: Success,
) {
    data class Success(
        val data: Data,
        val message: String,
    ) {
        data class Data(
            val acknowledged: Boolean,
            val matchedCount: Int,
            val modifiedCount: Int,
            val upsertedCount: Int,
            val upsertedId: Any,
        )
    }
}