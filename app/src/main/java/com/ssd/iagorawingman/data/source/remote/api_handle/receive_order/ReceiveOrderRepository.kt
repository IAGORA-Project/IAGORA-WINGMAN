package com.ssd.iagorawingman.data.source.remote.api_handle.receive_order

import com.ssd.iagorawingman.data.source.remote.body.AcceptedOrCancelledOrderBody
import com.ssd.iagorawingman.data.source.remote.response.ResAcceptedOrder
import retrofit2.Call

class ReceiveOrderRepository(
    private val receiveOrderApi: ReceiveOrderApi
): ReceiveOrderDataSource {

    override fun postAcceptedOrder(token: String, acceptedOrCancelledOrderBody: AcceptedOrCancelledOrderBody): Call<ResAcceptedOrder> {
        return receiveOrderApi.post_acceptedOrder("Bearer $token", acceptedOrCancelledOrderBody)
    }

    override fun postCancelledOrder(token: String, acceptedOrCancelledOrderBody: AcceptedOrCancelledOrderBody): Call<ResAcceptedOrder> {
        return receiveOrderApi.post_cancelledOrder("Bearer $token", acceptedOrCancelledOrderBody)
    }

}