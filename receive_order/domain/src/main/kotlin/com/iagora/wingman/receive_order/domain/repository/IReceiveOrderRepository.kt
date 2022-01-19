package com.iagora.wingman.receive_order.domain.repository

import com.iagora.wingman.core.util.SimpleResource
import com.iagora.wingman.receive_order.domain.models.body.ReceiveOrder

interface IReceiveOrderRepository {
    suspend fun postOrderAction(
        action: String,
        request: ReceiveOrder
    ): SimpleResource
}