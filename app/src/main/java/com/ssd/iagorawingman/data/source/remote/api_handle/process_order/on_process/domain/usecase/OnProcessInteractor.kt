package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.usecase

import androidx.paging.PagingData
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.model.ListWaitingOnProcess
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.repository.IOnProcessRepository
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow

class OnProcessInteractor(
    private val iRepository: IOnProcessRepository
) : OnProcessUseCase {

    override fun getAllListWaiting(): Flow<Resource<ListWaitingOnProcess>> =
        iRepository.getAllListWaiting()

}