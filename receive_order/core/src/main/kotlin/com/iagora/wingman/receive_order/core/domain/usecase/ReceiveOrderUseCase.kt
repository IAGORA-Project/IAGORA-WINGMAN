package com.iagora.wingman.receive_order.core.domain.usecase

import com.iagora.wingman.helper.Resource
import com.iagora.wingman.receive_order.helper.model.body.ReceiveOrder
import com.iagora.wingman.receive_order.helper.model.response.AcceptedOrder
import kotlinx.coroutines.flow.Flow

interface ReceiveOrderUseCase {
    fun postActionOrder(
        action: String,
        receiveOrder: ReceiveOrder,
    ): Flow<Resource<AcceptedOrder>>
}