package com.ssd.iagorawingman.data.source.remote.api_handle.receive_order

import com.ssd.iagorawingman.data.source.remote.body.ReceiveOrderBody
import com.ssd.iagorawingman.data.source.remote.response.ResAcceptedOrder
import retrofit2.Call

class ReceiveOrderRepository(
    private val receiveOrderApi: ReceiveOrderApi
): ReceiveOrderDataSource {

    override fun postAcceptedOrder(token: String, receiveOrderBody: ReceiveOrderBody): Call<ResAcceptedOrder> {
        return receiveOrderApi.post_acceptedOrder("Bearer $token", receiveOrderBody)
    }

    override fun postCancelledOrder(token: String, receiveOrderBody: ReceiveOrderBody): Call<ResAcceptedOrder> {
        return receiveOrderApi.post_cancelledOrder("Bearer $token", receiveOrderBody)
    }

}