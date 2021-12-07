package com.iagora.wingman.core.source.remote.api_handle.receive_order

import retrofit2.Call
import com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder

interface ReceiveOrderDataSource {

    fun postAcceptedOrder(receiveOrderBody: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody): Call<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>

    fun postCancelledOrder(receiveOrderBody: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody): Call<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>

}