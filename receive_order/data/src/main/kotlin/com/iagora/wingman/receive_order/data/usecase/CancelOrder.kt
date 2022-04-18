package com.iagora.wingman.receive_order.data.usecase

import com.iagora.wingman.receive_order.domain.models.body.ReceiveOrder
import com.iagora.wingman.receive_order.domain.repository.IReceiveOrderRepository
import com.iagora.wingman.receive_order.domain.usecase.ICancelOrder

class CancelOrder(
    private val repository: IReceiveOrderRepository
) : ICancelOrder {

    override suspend fun invoke(request: ReceiveOrder) =
        repository.postOrderAction("canceled", request)
}