package com.iagora.wingman.core.source.remote.api_handle.receive_order

import retrofit2.Call
import com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder

class ReceiveOrderRepository(
    private val receiveOrderApi: com.iagora.wingman.receive_order.core.ReceiveOrderApi
): ReceiveOrderDataSource {

    override fun postAcceptedOrder(receiveOrderBody: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody): Call<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder> {
        return receiveOrderApi.post_acceptedOrder( receiveOrderBody)
    }

    override fun postCancelledOrder(receiveOrderBody: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody): Call<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder> {
        return receiveOrderApi.post_cancelledOrder(receiveOrderBody)
    }

}