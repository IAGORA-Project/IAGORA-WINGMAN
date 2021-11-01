package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.repository

import androidx.paging.PagingData
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.model.ListWaitingOnProcess
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IOnProcessRepository {
    fun getAllListWaiting(): Flow<Resource<ListWaitingOnProcess>>
}