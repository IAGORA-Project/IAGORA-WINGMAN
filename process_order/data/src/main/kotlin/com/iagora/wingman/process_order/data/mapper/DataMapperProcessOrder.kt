package com.iagora.wingman.process_order.data.mapper


import com.iagora.wingman.core.data.mapper.MapperAddressUser.toModel
import com.iagora.wingman.core.data.mapper.MapperDataUser.toModel
import com.iagora.wingman.core.data.mapper.MapperProduct.toListModel
import com.iagora.wingman.process_order.core.data.remote.body.BargainBody
import com.iagora.wingman.process_order.core.data.remote.body.HandlingFeeBody
import com.iagora.wingman.process_order.data.remote.response.ResProcessOrder
import com.iagora.wingman.process_order.domain.model.response.ProcessOrder
import com.iagora.wingman.process_order.helper.model.body.Bargain
import com.iagora.wingman.process_order.helper.model.body.HandlingFee


object DataMapperProcessOrder {


    fun ResProcessOrder.ResGlobal.toModel() =
        ProcessOrder.Global(
            status ?: 404,
            success ?: ""
        )


    fun Bargain.toBody() = BargainBody(
        idProduct,
        idTransaction,
        newBargain,
        uom
    )

    fun HandlingFee.toBody() = HandlingFeeBody(
        price = price
    )

    fun ResProcessOrder.ResListOrder.toModel() =
        ProcessOrder.ListOrder(
            status = status ?: 404,
            success = success.toList()
        )


    private fun List<ResProcessOrder.ResListOrder.Success?>?.toList(): List<ProcessOrder.ListOrder.Success> {
        val successList = mutableListOf<ProcessOrder.ListOrder.Success>()

        this?.forEach { success ->
            success?.apply {
                successList.add(
                    ProcessOrder.ListOrder.Success(
                        dataUser = dataUser.toModel(),
                        grandTotal = grandTotal ?: 0,
                        idTransaction = idTransaction ?: "",
                        listProduct =
                        listProduct.toListModel(),
                        noOrder = noOrder ?: "",
                        status = status ?: "",
                        transactionDate = transactionDate ?: 0
                    )
                )
            }

        }
        return successList
    }

    fun ResProcessOrder.ResDetailOrder.toModel() =
        ProcessOrder.DetailOrder(
            status = status ?: 404,
            success = success.toModel()
        )

    private fun ResProcessOrder.ResDetailOrder.Success?.toModel() =
        ProcessOrder.DetailOrder.Success(
            address = this?.address.toModel(),
            handlingFee = this?.handlingFee ?: 0,
            totalPriceProduct = this?.totalPriceProduct ?: 0,
            dataUser = this?.dataUser.toModel(),
            grandTotal = this?.grandTotal ?: 0,
            idTransaction = this?.idTransaction ?: "",
            deliveryServices = this?.deliveryServices ?: "",
            noOrder = this?.noOrder ?: "",
            status = this?.status ?: "",
            transactionDate = this?.transactionDate ?: 0,
            listProduct = this?.listProduct.toListModel(),
            storeInfo = this?.storeInfo.toModel(),
            platformFee = this?.platformFee ?: 0,
            discount = this?.discount ?: 0,
            deliveryFee = this?.discount ?: 0,
            subTotal = this?.subTotal ?: 0
        )


    private fun ResProcessOrder.StoreInfo?.toModel() =
        ProcessOrder.StoreInfo(
            idStore = this?.idStore ?: "",
            storeName = this?.storeName ?: ""
        )


}