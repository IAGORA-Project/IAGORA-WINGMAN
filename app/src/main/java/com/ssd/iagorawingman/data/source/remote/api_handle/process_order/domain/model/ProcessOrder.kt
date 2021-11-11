package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


data class ProcessOrder(
    val listWaitingListOnProcessOrder: ListWaitingOnProcess,
    val detailWaitingListOnProcessOrder: DetailWaitingOnProcess,
    val global: Global,
    val detailWaitingListPath: DetailWaitingListPath
) {
    data class ListWaitingOnProcess(
        val status: Int,
        val success: List<Success>
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
        val success: Success
    ) {
        data class Success(
            val address: Address,
            val handlingFee: Long,
            val totalPriceProduct: Long,
            val dataUser: DataUser,
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
        val storeName: String
    )


    data class Global(
        val status: Int,
        val success: String
    )

    @Keep
    @Parcelize
    data class DetailWaitingListPath(
        val idTransaction: String,
        val typeWaiting: String,
    ) : Parcelable


    data class Address(
        val details: String,
        val fullName: String,
        val note: String,
        val phoneNumber: String
    )

    data class DataUser(
        val fullName: String,
        val idUser: String,
        val imgProfile: String,
        val phoneNumber: String
    )

    data class Product(
        val bargainPrice: Long,
        val idProduct: String,
        val productName: String,
        var qty: Int,
        val unit: String,
        val uom: Int
    )
}