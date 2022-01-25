package com.iagora.wingman.receive_order.data.mapper

import com.iagora.wingman.core.data.mapper.MapperDataUser.toModel
import com.iagora.wingman.core.data.mapper.MapperDataUser.toRem
import com.iagora.wingman.core.data.mapper.MapperProduct.toListModel
import com.iagora.wingman.core.data.mapper.MapperProduct.toListRem
import com.iagora.wingman.receive_order.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.receive_order.domain.models.body.ReceiveOrder

object MapperReceiveOrder {

    fun ReceiveOrder.toBody() =
        ReceiveOrderBody(
            idCart = idCart?:"",
            idMarket = idMarket?:"",
            index = index?:0,
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