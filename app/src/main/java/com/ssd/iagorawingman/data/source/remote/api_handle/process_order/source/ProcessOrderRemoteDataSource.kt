package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.source

import android.util.Log
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.ProcessOrderApi
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.data.source.remote.network.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import retrofit2.HttpException
import java.io.IOException

class ProcessOrderRemoteDataSource(
    private val services: ProcessOrderApi,
    private val externalScope: CoroutineScope
) {
    fun getAllListWaiting(
        token: String,
        typeWaiting: String
    ) = flow {
        try {
            val response = services.getAllListWaiting("Bearer $token", typeWaiting)
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)

    fun getDetailWaiting(
        token: String,
        idTransaction: String
    ) = flow {
        try {
            val response = services.getDetailListWaiting("Bearer $token", idTransaction)
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)

    fun postBargainPrice(
        token: String,
        bargainBody: BargainBody,
    ) = flow {
        try {
            val response = services.postBargainPrice("Bearer $token", bargainBody)
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)

    fun postActionTransaction(
        token: String,
        idTransaction: String,
        typeAction: String
    ) = flow {
        try {
            val response =
                services.postActionTransaction("Bearer $token", idTransaction, typeAction)
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)
}