package com.iagora.wingman.process_order.core

import com.iagora.wingman.core.source.remote.network.ApiResponse
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.process_order.core.domain.repository.IProcessOrderRepository
import com.iagora.wingman.process_order.core.source.remote.ProcessOrderRemoteDataSource
import com.iagora.wingman.process_order.helper.mapper.DataMapperProcessOrder.toBody
import com.iagora.wingman.process_order.helper.mapper.DataMapperProcessOrder.toModel
import com.iagora.wingman.process_order.helper.model.body.Bargain
import com.iagora.wingman.process_order.helper.model.body.HandlingFee
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ProcessOrderRepository(
    private val processOrderRemoteDataSource: ProcessOrderRemoteDataSource,
) : IProcessOrderRepository {


    override fun getAllListWaiting(typeWaiting: String): Flow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        flow {
            emit(Resource.loading())
            when (val response =
                processOrderRemoteDataSource.getAllListWaiting(
                    typeWaiting
                ).first()
                    ) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        response.data.toModel()
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(
                    response.errorMessage,
                    null))
            }
        }


    override fun getDetailListWaiting(
        idTransaction: String,
        typeWaiting: String,
    ): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        flow {
            emit(Resource.loading())
            when (val response =
                processOrderRemoteDataSource.getDetailWaiting(
                    idTransaction,
                    typeWaiting
                )
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        response.data.toModel()
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(
                    response.errorMessage,
                    null))
            }
        }

    override fun postBargainPrice(bargain: Bargain): Flow<Resource<ProcessOrder.Global>> =
        flow {
            emit(Resource.loading())
            when (val response =
                processOrderRemoteDataSource.postBargainPrice(bargain.toBody())
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        response.data.toModel()
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(
                    response.errorMessage,
                    null))
            }
        }


    override fun postNewHandlingFee(
        idTransaction: String,
        handlingFee: HandlingFee,
    ): Flow<Resource<ProcessOrder.Global>> = flow {
        emit(Resource.loading("true", null))
        when (val response =
            processOrderRemoteDataSource.postNewHandlingFee(
                idTransaction,
                handlingFee.toBody())
                .first()) {
            is ApiResponse.Success -> emit(
                Resource.success(
                    response.data.toModel()
                )
            )
            is ApiResponse.Error -> emit(Resource.error(
                response.errorMessage,
                null))
        }
    }

    override fun postActionTransaction(
        idTransaction: String,
        typeAction: String,
    ): Flow<Resource<ProcessOrder.Global>> =

        flow {
            emit(Resource.loading("true", null))
            when (val response =
                processOrderRemoteDataSource.postActionTransaction(

                    idTransaction,
                    typeAction
                )
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        response.data.toModel()
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(
                    response.errorMessage,
                    null))
            }
        }
}