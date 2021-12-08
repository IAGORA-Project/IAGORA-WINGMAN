package com.iagora.wingman.process_order.helper.model.response

import com.iagora.wingman.helper.model.AddressUser
import com.iagora.wingman.helper.model.DataUser
import com.iagora.wingman.helper.model.Product


data class ProcessOrder(
    val listWaitingListOnProcessOrder: ListWaitingOnProcess,
    val detailWaitingListOnProcessOrder: DetailWaitingOnProcess,
    val global: Global,
    val detailWaitingListPath: DetailWaitingListPath,
) {
    data class ListWaitingOnProcess(
        val status: Int,
        val success: List<Success>,
    ) {

        data class Success(
            val dataUser: DataUser,
            val grandTotal: Long,
            val idTransaction: String,
            val listProduct: List<Product>,
            val noOrder: String,
            val status: String,
            val transactionDate: Long,
        )
    }


    data class DetailWaitingOnProcess(
        val status: Int,
        val success: Success,
    ) {
        data class Success(
            val address: AddressUser,
            val handlingFee: Long,
            val totalPriceProduct: Long,
            val dataUser:DataUser,
            val grandTotal: Long,
            val idTransaction: String,
            val deliveryServices: String,
            val listProduct: List<Product>,
            val noOrder: String,
            val status: String,
            val transactionDate: Long,
            val storeInfo: StoreInfo,
            val platformFee: Long,
            val discount: Long,
            val deliveryFee: Long,
            val subTotal: Long,
        )
    }

    data class StoreInfo(
        val idStore: String,
        val storeName: String,
    )


    data class Global(
        val status: Int,
        val success: String,
    )


    data class DetailWaitingListPath(
        val idTransaction: String,
        val typeWaiting: String,
    )

}