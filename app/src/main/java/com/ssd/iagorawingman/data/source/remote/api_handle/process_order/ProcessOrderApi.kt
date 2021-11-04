package com.ssd.iagorawingman.data.source.remote.api_handle.process_order


import com.ssd.iagorawingman.data.source.remote.response.ResGetProcessOrder
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProcessOrderApi {
    @GET("transaction/WAITING_CONFIRMATION")
    suspend fun getAllListWaiting(
        @Header("Authorization") authorization: String,
    ): ResGetProcessOrder.ResGetListWaitingOnProcessOrder

    @GET("transaction/WAITING_CONFIRMATION/{id_transaction}")
    suspend fun getDetailListWaiting(
        @Header("Authorization") authorization: String,
        @Path("id_transaction") idTransaction: String
    ): ResGetProcessOrder.ResGetDetailWaitingListOnProcessOrder
}