package com.iagora.wingman.receive_order.helper.mapper

import com.iagora.wingman.helper.mapper.DataMapper.mapModelDataUserToRemDataUser
import com.iagora.wingman.helper.mapper.DataMapper.mapModelProductToListModelProduct
import com.iagora.wingman.helper.mapper.DataMapper.mapRemDataUserToModelDataUser
import com.iagora.wingman.helper.mapper.DataMapper.mapRemProductToListRemProduct
import com.iagora.wingman.receive_order.helper.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.receive_order.helper.data.remote.response.ResAcceptedOrder
import com.iagora.wingman.receive_order.helper.model.body.ReceiveOrder
import com.iagora.wingman.receive_order.helper.model.response.AcceptedOrder

object DataMapperReceiveOrder {
    fun mapResponseAcceptedOrderToModelAcceptedOrder(input: ResAcceptedOrder) =
        AcceptedOrder(
            success = mapResponseAcceptedOrderSuccessToModelAcceptedOrderSuccess(input.success),
            status = input.status ?: 0
        )

    private fun mapResponseAcceptedOrderSuccessToModelAcceptedOrderSuccess(input: ResAcceptedOrder.Success? = null) =
        AcceptedOrder.Success(
            data = mapResponseAcceptedOrderSuccessDataToModelAcceptedOrderSuccessData(input?.data),
            message = input?.message ?: "no-message"
        )

    private fun mapResponseAcceptedOrderSuccessDataToModelAcceptedOrderSuccessData(input: ResAcceptedOrder.Success.Data? = null) =
        AcceptedOrder.Success.Data(
            acknowledged = input?.acknowledged ?: false,
            matchedCount = input?.matchedCount ?: 0,
            modifiedCount = input?.modifiedCount ?: 0,
            upsertedCount = input?.upsertedCount ?: 0,
            upsertedId = input?.upsertedId ?: ""
        )

    fun mapModelReceiveOrderToReceiveOrderBody(input: ReceiveOrder) =
        ReceiveOrderBody(
            idCart = input.idCart,
            idMarket = input.idMarket,
            index = input.index,
            listProduct = mapModelProductToListModelProduct(input.listProduct),
            dataUser = mapModelDataUserToRemDataUser(input.dataUser)
        )

    fun mapReceiveOrderBodyToModelReceiveOrder(input: ReceiveOrderBody) =
        ReceiveOrder(
            idCart = input.idCart,
            idMarket = input.idMarket,
            index = input.index,
            listProduct = mapRemProductToListRemProduct(input.listProduct),
            dataUser = mapRemDataUserToModelDataUser(input.dataUser)
        )
}