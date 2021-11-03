package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.repository

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ListWaitingOnProcess
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IProcessOrderRepository {
    fun getAllListWaiting(): Flow<Resource<ListWaitingOnProcess>>
}