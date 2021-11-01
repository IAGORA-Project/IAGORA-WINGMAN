package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process

import androidx.paging.PagingData
import com.ssd.iagorawingman.BuildConfig
import com.ssd.iagorawingman.data.source.local.shared_handle.auth.SharedAuthRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.model.ListWaitingOnProcess
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.repository.IOnProcessRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.source.OnProcessRemoteDataSource
import com.ssd.iagorawingman.data.source.remote.network.ApiResponse
import com.ssd.iagorawingman.utils.DataMapper
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class OnProcessRepository(
    private val remoteDataSource: OnProcessRemoteDataSource,
     sharedAuth: SharedAuthRepository
) : IOnProcessRepository {

    private val token = sharedAuth.getAuth(BuildConfig.KEY_SHARED_PREFERENCE_AUTH)

    override fun getAllListWaiting(): Flow<Resource<ListWaitingOnProcess>> =
        flow {
            emit(Resource.loading("true", null))
            when (val response = remoteDataSource.getAllListWaiting(token?.success?.token!!).first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        DataMapper.mapResponseWaitingListToDomainWaitingList(response.data)
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(response.errorMessage, null))
            }
        }
}