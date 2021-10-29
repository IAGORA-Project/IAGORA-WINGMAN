package com.ssd.iagorawingman.data.source.remote.api_handle.receive_order

import com.ssd.iagorawingman.data.source.remote.body.AcceptedOrCancelledOrderBody
import com.ssd.iagorawingman.data.source.remote.response.ResAcceptedOrder
import retrofit2.Call

interface ReceiveOrderDataSource {

    fun postAcceptedOrder(token: String, acceptedOrCancelledOrderBody: AcceptedOrCancelledOrderBody): Call<ResAcceptedOrder>

    fun postCancelledOrder(token: String, acceptedOrCancelledOrderBody: AcceptedOrCancelledOrderBody): Call<ResAcceptedOrder>

}