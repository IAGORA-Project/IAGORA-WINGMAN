package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ListWaitingOnProcess
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow


interface ProcessOrderUseCase {
    fun getAllListWaiting(): Flow<Resource<ListWaitingOnProcess>>
}