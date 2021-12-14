package com.iagora.wingman.receive_order.helper.mapper

import com.iagora.wingman.helper.mapper.MapperDataUser.toModel
import com.iagora.wingman.helper.mapper.MapperDataUser.toRem
import com.iagora.wingman.helper.mapper.MapperProduct.toListModel
import com.iagora.wingman.helper.mapper.MapperProduct.toListRem
import com.iagora.wingman.receive_order.helper.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.receive_order.helper.data.remote.response.ResAcceptedOrder
import com.iagora.wingman.receive_order.helper.model.body.ReceiveOrder
import com.iagora.wingman.receive_order.helper.model.response.AcceptedOrder

object MapperReceiveOrder {

    fun ReceiveOrder.toBody() =
        ReceiveOrderBody(
            idCart = idCart,
            idMarket = idMarket,
            index = index,
            listProduct = listProduct.toListRem(),
            dataUser = dataUser.toRem()
        )

    fun ReceiveOrderBody.toModel() =
        ReceiveOrder(
            idCart = idCart,
            idMarket = idMarket,
            index = index,
            listProduct = listProduct.toListModel(),
            dataUser = dataUser.toModel()
        )
}