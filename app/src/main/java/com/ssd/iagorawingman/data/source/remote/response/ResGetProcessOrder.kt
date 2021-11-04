package com.ssd.iagorawingman.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResGetProcessOrder(
    var resGetListWaitingOnProcessOrder: ResGetListWaitingOnProcessOrder? = null,
    var resGetDetailWaitingListOnProcessOrder: ResGetDetailWaitingListOnProcessOrder? = null
) {
    data class ResGetListWaitingOnProcessOrder(
        @SerializedName("status") var status: Int? = null,
        @SerializedName("success") var success: List<Success?>? = null
    ) {
        data class Success(
            @SerializedName("data_user") var dataUser: DataUser? = null,
            @SerializedName("grand_total") var grandTotal: Long? = null,
            @SerializedName("id_transaction") var idTransaction: String? = null,
            @SerializedName("list_product") var listProduct: List<Product?>? = null,
            @SerializedName("no_order") var noOrder: String? = null,
            @SerializedName("status") var status: String? = null,
            @SerializedName("transaction_date") var transactionDate: Long? = null
        )
    }

    data class ResGetDetailWaitingListOnProcessOrder(
        @SerializedName("status") var status: Int? = null,
        @SerializedName("success") var success: Success? = null
    ) {
        data class Success(
            @SerializedName("adress") var address: Address? = null,
            @SerializedName("biaya_penanganan") var handlingFee: Long? = null,
            @SerializedName("belanja") var totalPriceProduct: Long? = null,
            @SerializedName("data_user") var dataUser: DataUser? = null,
            @SerializedName("grand_total") var grandTotal: Long? = null,
            @SerializedName("id_transaction") var idTransaction: String? = null,
            @SerializedName("layanan_pengiriman") var deliveryServices: String? = null,
            @SerializedName("list_product") var listProduct: List<Product?>? = null,
            @SerializedName("no_order") var noOrder: String? = null,
            @SerializedName("status") var status: String? = null,
            @SerializedName("transaction_date") var transactionDate: Long? = null,
            @SerializedName("pasar_info") var storeInfo: StoreInfo? = null,
            @SerializedName("biaya_platform") var platformFee:  Long? = null,
            @SerializedName("discount") var discount: Long? = null,
            @SerializedName("ongkir") var deliveryFee: Long? = null,
            @SerializedName("sub_total") var subTotal: Long? = null,
        )
    }


    data class StoreInfo(
        @SerializedName("id_pasar") val idStore: String,
        @SerializedName("nama_pasar") val storeName: String
    )

    data class Address(
        @SerializedName("details") var details: String? = null,
        @SerializedName("full_name") var fullName: String? = null,
        @SerializedName("note") var note: String? = null,
        @SerializedName("phone_number") var phoneNumber: String? = null
    )

    data class DataUser(
        @SerializedName("full_name") var fullName: String? = null,
        @SerializedName("id_user") var idUser: String? = null,
        @SerializedName("img_profile") var imgProfile: String? = null,
        @SerializedName("phone_number") var phoneNumber: String? = null
    )

    data class Product(
        @SerializedName("bergain_price") var bargainPrice: Long? = null,
        @SerializedName("id_product") var idProduct: String? = null,
        @SerializedName("product_name") var productName: String? = null,
        @SerializedName("qty") var qty: Int? = null,
        @SerializedName("satuan") var unit: String? = null,
        @SerializedName("uom") var uom: Int? = null,
    )
}