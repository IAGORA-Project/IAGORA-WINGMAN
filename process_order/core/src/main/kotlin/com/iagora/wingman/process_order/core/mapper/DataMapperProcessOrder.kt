package com.iagora.wingman.process_order.core.mapper

import com.iagora.wingman.core.mapper.MapperAddressUser.toModel
import com.iagora.wingman.core.mapper.MapperDataUser.toModel
import com.iagora.wingman.core.mapper.MapperProduct.toListModel
import com.iagora.wingman.process_order.core.data.remote.body.BargainBody
import com.iagora.wingman.process_order.core.data.remote.body.HandlingFeeBody
import com.iagora.wingman.process_order.core.data.remote.response.ResGetProcessOrder
import com.iagora.wingman.process_order.helper.model.body.Bargain
import com.iagora.wingman.process_order.helper.model.body.HandlingFee
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder


object DataMapperProcessOrder {


    fun ResGetProcessOrder.ResGlobal.toModel() =
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

    fun ResGetProcessOrder.ResGetListWaitingOnProcessOrder.toModel() =
        ProcessOrder.ListWaitingOnProcess(
            status = status ?: 404,
            success = success.toList()
        )


    private fun List<ResGetProcessOrder.ResGetListWaitingOnProcessOrder.Success?>?.toList(): List<ProcessOrder.ListWaitingOnProcess.Success> {
        val successList = mutableListOf<ProcessOrder.ListWaitingOnProcess.Success>()

        this?.forEach { success ->
            success?.apply {
                successList.add(
                    ProcessOrder.ListWaitingOnProcess.Success(
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

    fun ResGetProcessOrder.ResGetDetailWaitingListOnProcessOrder.toModel() =
        ProcessOrder.DetailWaitingOnProcess(
            status = status ?: 404,
            success = success.toModel()
        )

    private fun ResGetProcessOrder.ResGetDetailWaitingListOnProcessOrder.Success?.toModel() =
        ProcessOrder.DetailWaitingOnProcess.Success(
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


    private fun ResGetProcessOrder.StoreInfo?.toModel() =
        ProcessOrder.StoreInfo(
            idStore = this?.idStore ?: "",
            storeName = this?.storeName ?: ""
        )


}