package com.iagora.wingman.process_order.core.helper

import com.iagora.wingman.core.helper.DataMapper.mapRemAddressUserToModelAddressUser
import com.iagora.wingman.core.helper.DataMapper.mapRemDataUserToModelDataUser
import com.iagora.wingman.core.helper.DataMapper.mapRemProductToListRemProduct
import com.iagora.wingman.process_order.core.data.remote.body.BargainBody
import com.iagora.wingman.process_order.core.data.remote.body.HandlingFeeBody
import com.iagora.wingman.process_order.core.data.remote.response.ResGetProcessOrder
import com.iagora.wingman.process_order.helper.model.body.Bargain
import com.iagora.wingman.process_order.helper.model.body.HandlingFee
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder


object DataMapperProcessOrder {


    fun mapResponseBargainPriceToModelBargainPrice(input: ResGetProcessOrder.ResGlobal) =
        ProcessOrder.Global(
            input.status ?: 404,
            input.success ?: ""
        )


    fun mapBargainToBargainBody(input: Bargain) = BargainBody(
        input.idProduct,
        input.idTransaction,
        input.newBargain,
        input.uom
    )

    fun mapHandlingFeeToHandlingFeeBody(input: HandlingFee) = HandlingFeeBody(
        price = input.price
    )

    fun mapResponseWaitingListToModelWaitingList(input: ResGetProcessOrder.ResGetListWaitingOnProcessOrder) =
        ProcessOrder.ListWaitingOnProcess(
            status = input.status ?: 404,
            success = mapResponseWaitingListSuccessToModelWaitingListSuccess(
                input.success
            )
        )


    private fun mapResponseWaitingListSuccessToModelWaitingListSuccess(
        input: List<ResGetProcessOrder.ResGetListWaitingOnProcessOrder.Success?>?,
    ): List<ProcessOrder.ListWaitingOnProcess.Success> {
        val successList = mutableListOf<ProcessOrder.ListWaitingOnProcess.Success>()

        input?.forEach { success ->
            success?.apply {
                successList.add(
                    ProcessOrder.ListWaitingOnProcess.Success(
                        dataUser = mapRemDataUserToModelDataUser(
                            dataUser
                        ),
                        grandTotal = grandTotal ?: 0,
                        idTransaction = idTransaction ?: "",
                        listProduct =
                        mapRemProductToListRemProduct(
                            listProduct

                        ),
                        noOrder = noOrder ?: "",
                        status = status ?: "",
                        transactionDate = transactionDate ?: 0
                    )
                )
            }

        }
        return successList
    }

    fun mapResponseGetDetailWaitingListOnProcessOrderToModelDetailWaitingOnProcess(input: ResGetProcessOrder.ResGetDetailWaitingListOnProcessOrder) =
        ProcessOrder.DetailWaitingOnProcess(
            status = input.status ?: 404,
            success = mapResponseGetDetailWaitingListOnProcessSuccessToModelDetailWaitingProcessSuccess(
                input.success
            )
        )

    private fun mapResponseGetDetailWaitingListOnProcessSuccessToModelDetailWaitingProcessSuccess(
        input: ResGetProcessOrder.ResGetDetailWaitingListOnProcessOrder.Success? = null,
    ) =
        ProcessOrder.DetailWaitingOnProcess.Success(
            address = mapRemAddressUserToModelAddressUser(
                input?.address
            ),
            handlingFee = input?.handlingFee ?: 0,
            totalPriceProduct = input?.totalPriceProduct ?: 0,
            dataUser = mapRemDataUserToModelDataUser(
                input?.dataUser
            ),
            grandTotal = input?.grandTotal ?: 0,
            idTransaction = input?.idTransaction ?: "",
            deliveryServices = input?.deliveryServices ?: "",
            noOrder = input?.noOrder ?: "",
            status = input?.status ?: "",
            transactionDate = input?.transactionDate ?: 0,
            listProduct = mapRemProductToListRemProduct(input?.listProduct),
            storeInfo = mapResponseProcessOrderStoreInfoToModelProcessOrderStoreInfo(input?.storeInfo),
            platformFee = input?.platformFee ?: 0,
            discount = input?.discount ?: 0,
            deliveryFee = input?.discount ?: 0,
            subTotal = input?.subTotal ?: 0
        )


    private fun mapResponseProcessOrderStoreInfoToModelProcessOrderStoreInfo(input: ResGetProcessOrder.StoreInfo? = null) =
        ProcessOrder.StoreInfo(
            idStore = input?.idStore ?: "",
            storeName = input?.storeName ?: ""
        )


}