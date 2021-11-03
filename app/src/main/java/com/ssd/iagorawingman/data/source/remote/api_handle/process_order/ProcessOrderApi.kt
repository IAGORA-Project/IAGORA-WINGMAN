package com.ssd.iagorawingman.data.source.remote.api_handle.process_order

import com.ssd.iagorawingman.data.source.remote.response.ResGetListWaitingOnProcessOrder
import retrofit2.http.GET
import retrofit2.http.Header

interface ProcessOrderApi {
    @GET("transaction/WAITING_CONFIRMATION")
    suspend fun getAllListWaiting(
        @Header("Authorization") authorization: String,
    ):ResGetListWaitingOnProcessOrder
}