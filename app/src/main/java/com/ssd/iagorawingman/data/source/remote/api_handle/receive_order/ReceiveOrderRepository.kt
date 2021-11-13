package com.ssd.iagorawingman.data.source.remote.api_handle.receive_order

import com.ssd.iagorawingman.data.source.remote.body.ReceiveOrderBody
import com.ssd.iagorawingman.data.source.remote.response.ResAcceptedOrder
import retrofit2.Call

class ReceiveOrderRepository(
    private val receiveOrderApi: ReceiveOrderApi
): ReceiveOrderDataSource {

    override fun postAcceptedOrder(receiveOrderBody: ReceiveOrderBody): Call<ResAcceptedOrder> {
        return receiveOrderApi.post_acceptedOrder( receiveOrderBody)
    }

    override fun postCancelledOrder(receiveOrderBody: ReceiveOrderBody): Call<ResAcceptedOrder> {
        return receiveOrderApi.post_cancelledOrder(receiveOrderBody)
    }

}