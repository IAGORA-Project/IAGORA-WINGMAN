package com.iagora.wingman.receive_order.core.domain.repository

import com.iagora.wingman.helper.Resource
import com.iagora.wingman.receive_order.core.model.body.ReceiveOrder
import com.iagora.wingman.receive_order.core.model.response.AcceptedOrder
import kotlinx.coroutines.flow.Flow

interface IReceiveOrderRepository {
    fun postActionOrder(
        action: String,
        receiveOrder: ReceiveOrder,
    ): Flow<Resource<AcceptedOrder>>
}