package com.ssd.iagorawingman.data.source.remote.api_handle.receive_order

import com.ssd.iagorawingman.data.source.remote.body.ReceiveOrderBody
import com.ssd.iagorawingman.data.source.remote.response.ResAcceptedOrder
import retrofit2.Call

interface ReceiveOrderDataSource {

    fun postAcceptedOrder(token: String, receiveOrderBody: ReceiveOrderBody): Call<ResAcceptedOrder>

    fun postCancelledOrder(token: String, receiveOrderBody: ReceiveOrderBody): Call<ResAcceptedOrder>

}