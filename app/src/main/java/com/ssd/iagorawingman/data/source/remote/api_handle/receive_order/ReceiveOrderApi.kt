package com.ssd.iagorawingman.data.source.remote.api_handle.receive_order

import com.ssd.iagorawingman.data.source.remote.body.ReceiveOrderBody
import com.ssd.iagorawingman.data.source.remote.response.ResAcceptedOrder
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ReceiveOrderApi {

    @POST("accepted")
    fun post_acceptedOrder(
        @Header("Authorization") authorization: String,
        @Body receiveOrderBody: ReceiveOrderBody
    ): Call<ResAcceptedOrder>


    @POST("canceled")
    fun post_cancelledOrder(
        @Header("Authorization") authorization: String,
        @Body receiveOrderBody: ReceiveOrderBody
    ): Call<ResAcceptedOrder>
}