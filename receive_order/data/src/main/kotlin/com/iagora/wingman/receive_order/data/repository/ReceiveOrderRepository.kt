package com.iagora.wingman.receive_order.data.repository

import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource
import com.iagora.wingman.core.util.UiText
import com.iagora.wingman.receive_order.data.R
import com.iagora.wingman.receive_order.data.mapper.MapperReceiveOrder.toBody
import com.iagora.wingman.receive_order.data.remote.ReceiveOrderApi
import com.iagora.wingman.receive_order.domain.models.body.ReceiveOrder
import com.iagora.wingman.receive_order.domain.repository.IReceiveOrderRepository
import retrofit2.HttpException
import java.io.IOException

class ReceiveOrderRepository(
    private val api: ReceiveOrderApi,
) : IReceiveOrderRepository {

    override suspend fun postOrderAction(
        action: String,
        request: ReceiveOrder,
    ): SimpleResource {
        return try {
            api.postOrderAction(action, request.toBody())
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }
}