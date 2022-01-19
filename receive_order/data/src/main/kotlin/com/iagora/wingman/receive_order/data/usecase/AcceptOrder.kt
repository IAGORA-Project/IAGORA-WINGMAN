package com.iagora.wingman.receive_order.data.usecase

import com.iagora.wingman.receive_order.domain.models.body.ReceiveOrder
import com.iagora.wingman.receive_order.domain.repository.IReceiveOrderRepository
import com.iagora.wingman.receive_order.domain.usecase.IAcceptOrder

class AcceptOrder(
    private val repository: IReceiveOrderRepository
) : IAcceptOrder {

    override suspend fun invoke(request: ReceiveOrder) =
        repository.postOrderAction("accepted", request)
}