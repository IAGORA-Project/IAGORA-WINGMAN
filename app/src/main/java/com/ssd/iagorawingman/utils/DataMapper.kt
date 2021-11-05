package com.ssd.iagorawingman.utils

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.response.ResGetProcessOrder


object DataMapper {
    fun mapResponseBargainPriceToDomainBargainPrice(input: ResGetProcessOrder.ResGlobal) =
        ProcessOrder.Global(
            input.status ?: 404,
            input.success ?: ""
        )


    fun mapResponseWaitingListToDomainWaitingList(input: ResGetProcessOrder.ResGetListWaitingOnProcessOrder) =
        ProcessOrder.ListWaitingOnProcess(
            status = input.status ?: 404,
            success = mapResponseWaitingListSuccessToDomainWaitingListSuccess(
                input.success
            )
        )


    private fun mapResponseWaitingListSuccessToDomainWaitingListSuccess(
        input: List<ResGetProcessOrder.ResGetListWaitingOnProcessOrder.Success?>?
    ): List<ProcessOrder.ListWaitingOnProcess.Success> {
        val successList = mutableListOf<ProcessOrder.ListWaitingOnProcess.Success>()

        input?.forEach { success ->
            success?.apply {
                successList.add(
                    ProcessOrder.ListWaitingOnProcess.Success(
                        dataUser = mapResponseProcessOrderDataUserToDomainProcessOrderDataUser(
                            dataUser
                        ),
                        grandTotal = grandTotal ?: 0,
                        idTransaction = idTransaction ?: "",
                        listProduct =
                        mapResponseProcessOrderListProductToDomainProcessOrderListProduct(
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

    fun mapResponseGetDetailWaitingListOnProcessOrderToDetailWaitingOnProcess(input: ResGetProcessOrder.ResGetDetailWaitingListOnProcessOrder) =
        ProcessOrder.DetailWaitingOnProcess(
            status = input.status ?: 404,
            success = mapResponseGetDetailWaitingListOnProcessSuccessToDomainDetailWaitingProcessSuccess(
                input.success
            )
        )

    private fun mapResponseGetDetailWaitingListOnProcessSuccessToDomainDetailWaitingProcessSuccess(
        input: ResGetProcessOrder.ResGetDetailWaitingListOnProcessOrder.Success? = null
    ) =
        ProcessOrder.DetailWaitingOnProcess.Success(
            address = mapResponseProcessOrderAddressToDomainProcessOrderAddress(
                input?.address
            ),
            handlingFee = input?.handlingFee ?: 0,
            totalPriceProduct = input?.totalPriceProduct ?: 0,
            dataUser = mapResponseProcessOrderDataUserToDomainProcessOrderDataUser(
                input?.dataUser
            ),
            grandTotal = input?.grandTotal ?: 0,
            idTransaction = input?.idTransaction ?: "",
            deliveryServices = input?.deliveryServices ?: "",
            noOrder = input?.noOrder ?: "",
            status = input?.status ?: "",
            transactionDate = input?.transactionDate ?: 0,
            listProduct = mapResponseProcessOrderListProductToDomainProcessOrderListProduct(input?.listProduct),
            storeInfo = mapResponseProcessOrderStoreInfoToDomainProcessOrderStoreInfo(input?.storeInfo),
            platformFee = input?.platformFee ?: 0,
            discount = input?.discount ?: 0,
            deliveryFee = input?.discount ?: 0,
            subTotal = input?.subTotal ?: 0
        )


    private fun mapResponseProcessOrderStoreInfoToDomainProcessOrderStoreInfo(input: ResGetProcessOrder.StoreInfo? = null) =
        ProcessOrder.StoreInfo(
            idStore = input?.idStore ?: "",
            storeName = input?.storeName ?: ""
        )


    private fun mapResponseProcessOrderDataUserToDomainProcessOrderDataUser(input: ResGetProcessOrder.DataUser? = null) =
        ProcessOrder.DataUser(
            input?.fullName ?: "",
            input?.idUser ?: "",
            input?.imgProfile ?: "",
            input?.phoneNumber ?: ""
        )

    private fun mapResponseProcessOrderListProductToDomainProcessOrderListProduct(
        input: List<ResGetProcessOrder.Product?>? = null
    ): List<ProcessOrder.Product> {
        val listProduct = mutableListOf<ProcessOrder.Product>()
        input?.forEach { product ->
            listProduct.add(
                ProcessOrder.Product(
                    bargainPrice = product?.bargainPrice ?: 0,
                    productName = product?.productName ?: "",
                    idProduct = product?.idProduct ?: "",
                    unit = product?.unit ?: "unit",
                    uom = product?.uom ?: 0,
                    qty = product?.qty ?: 0
                )
            )

        }

        return listProduct
    }


    private fun mapResponseProcessOrderAddressToDomainProcessOrderAddress(
        input: ResGetProcessOrder.Address? = null
    ) = ProcessOrder.Address(
        details = input?.details ?: "",
        fullName = input?.fullName ?: "",
        note = input?.note ?: "",
        phoneNumber = input?.phoneNumber ?: ""
    )


}