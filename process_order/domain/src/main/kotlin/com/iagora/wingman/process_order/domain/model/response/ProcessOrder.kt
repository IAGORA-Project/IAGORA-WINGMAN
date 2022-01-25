package com.iagora.wingman.process_order.domain.model.response

import com.iagora.wingman.core.domain.model.dto.AddressUser
import com.iagora.wingman.core.domain.model.dto.DataUser
import com.iagora.wingman.core.domain.model.dto.Product


data class ProcessOrder(
    val listOrder: ListOrder,
    val detailOrder: DetailOrder,
    val global: Global,
    val detailOrderPath: DetailOrderPath,
) {
    data class ListOrder(
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


    data class DetailOrder(
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


    data class DetailOrderPath(
        val idTransaction: String,
        val typeWaiting: String,
    )

}