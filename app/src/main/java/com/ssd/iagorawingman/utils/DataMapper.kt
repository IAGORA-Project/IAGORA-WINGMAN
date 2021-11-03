package com.ssd.iagorawingman.utils

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ListWaitingOnProcess
import com.ssd.iagorawingman.data.source.remote.response.ResGetListWaitingOnProcessOrder

object DataMapper {
    fun mapResponseWaitingListToDomainWaitingList(input: ResGetListWaitingOnProcessOrder) =
        ListWaitingOnProcess(
            status = input.status ?: 0,
            success = mapResponseWaitingListSuccessToDomainWaitingListSuccess(
                input.success
            )
        )

    private fun mapResponseWaitingListSuccessToDomainWaitingListSuccess(
        input: List<ResGetListWaitingOnProcessOrder.Succes?>?
    ): List<ListWaitingOnProcess.Success> {
        val successList = mutableListOf<ListWaitingOnProcess.Success>()

        input?.forEach { success ->
            success?.apply {
                successList.add(
                    ListWaitingOnProcess.Success(
                        dataUser = mapResponseWaitingListSuccessDataUserToDomainWaitingListSuccessDataUser(
                            dataUser
                        ),
                        grandTotal = grandTotal ?: 0,
                        idTransaction = idTransaction ?: "",
                        listProduct =
                        mapResponseWaitingListSuccessProductToDomainWaitingListSuccessProduct(
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

    private fun mapResponseWaitingListSuccessDataUserToDomainWaitingListSuccessDataUser(input: ResGetListWaitingOnProcessOrder.Succes.DataUser? = null) =
        ListWaitingOnProcess.Success.DataUser(
            input?.fullName ?: "",
            input?.idUser ?: "",
            input?.imgProfile ?: "",
            input?.phoneNumber ?: ""
        )

    private fun mapResponseWaitingListSuccessProductToDomainWaitingListSuccessProduct(input: List<ResGetListWaitingOnProcessOrder.Succes.Product?>? = null): List<ListWaitingOnProcess.Success.Product> {
        val productList = mutableListOf<ListWaitingOnProcess.Success.Product>()
        input?.forEach { product ->
            productList.add(
                ListWaitingOnProcess.Success.Product(
                    product?.avgPrice ?: 0,
                    product?.bargainPrice ?: 0,
                    product?.idProduct ?: "",
                    product?.productName ?: "",
                    product?.qty ?: 0,
                    product?.satuan ?: "",
                    product?.uom ?: 0
                )
            )

        }

        return productList
    }

}