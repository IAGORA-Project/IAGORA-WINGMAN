package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.source

import android.util.Log
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.OnProcessApi
import com.ssd.iagorawingman.data.source.remote.network.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import retrofit2.HttpException
import java.io.IOException

class OnProcessRemoteDataSource(
    private val services: OnProcessApi,
    private val externalScope: CoroutineScope
) {
    fun getAllListWaiting(
        token: String
    ) = flow {
        try {
            val response = services.getAllListWaiting("Bearer $token")
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 1)
}