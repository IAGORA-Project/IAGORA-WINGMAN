package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model

data class ListWaitingOnProcess(
    val status: Int,
    val success: List<Success>
) {

    data class Success(
        val dataUser: DataUser,
        val grandTotal: Int,
        val idTransaction: String,
        val listProduct: List<Product>,
        val noOrder: String,
        val status: String,
        val transactionDate: Long
    ) {
        data class DataUser(
            val fullName: String,
            val idUser: String,
            val imgProfile: String,
            val phoneNumber: String
        )

        data class Product(
            val avgPrice: Int,
            val bargainPrice: Int,
            val idProduct: String,
            val productName: String,
            val qty: Int,
            val satuan: String,
            val uom: Int
        )
    }
}
