package com.iagora.wingman.receive_order.core.mapper

import com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder
import com.iagora.wingman.receive_order.helper.model.response.AcceptedOrder

object MapperAcceptedOrder {
    fun ResAcceptedOrder.toModel() =
        AcceptedOrder(
            success = success.toModel(),
            status = status ?: 0
        )

    private fun ResAcceptedOrder.Success?.toModel() =
        AcceptedOrder.Success(
            data = this?.data.toModel(),
            message = this?.message ?: "no-message"
        )

    private fun ResAcceptedOrder.Success.Data?.toModel() =
        AcceptedOrder.Success.Data(
            acknowledged = this?.acknowledged ?: false,
            matchedCount = this?.matchedCount ?: 0,
            modifiedCount = this?.modifiedCount ?: 0,
            upsertedCount = this?.upsertedCount ?: 0,
            upsertedId = this?.upsertedId ?: ""
        )
}